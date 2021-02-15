package com.hiberus.manager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiberus.manager.client.PriceGeneratorClient;
import com.hiberus.manager.domain.Ticket;
import com.hiberus.manager.domain.User;
import com.hiberus.manager.dto.PriceGeneratorDTO;
import com.hiberus.manager.dto.TicketDTO;
import com.hiberus.manager.repository.TicketRepository;
import com.hiberus.manager.repository.UserRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private PriceGeneratorClient priceGeneratorClient;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public Optional<TicketDTO> searchRoute(String city, String transportType) throws Exception {
		PriceGeneratorDTO searchResult = this.priceGeneratorClient.priceCalculator(city, transportType);
		if(searchResult == null) {
			return Optional.of(null);
		}
		return Optional.of(generateTicket(searchResult));
	}

	@Override
	public TicketDTO createTicket(TicketDTO ticketDTO) {
		User user = saveUser(ticketDTO.getUser());
		return convertToTicketDTO(saveNewTicket(ticketDTO, user));
	}
	
	@Override
	public Optional<TicketDTO> findTicketDTO(Long id) {
		Optional<Ticket> ticket = this.ticketRepository.findById(id);
		if(!ticket.isPresent()) {
			return Optional.of(null);
		}
		return Optional.of(convertToTicketDTO(ticket.get()));
	}

	private TicketDTO generateTicket(PriceGeneratorDTO priceGeneratorDTO) {
		return new TicketDTO(null, "", priceGeneratorDTO.getDestination().getCity(), priceGeneratorDTO.getTransport().getTransportType(), 1,
				priceGeneratorDTO.getPrice(), priceGeneratorDTO.getPrice());
	}
	
	@Transactional(readOnly = false)
	private User saveUser(String name) {
		User user = this.userRepository.findByName(name).get();
		return user != null ?  user : this.userRepository.save(new User(name));
	}
	
	private Ticket saveNewTicket(TicketDTO ticketDTO, User user) {
		return this.ticketRepository
				.save(new Ticket(ticketDTO.getCity(), ticketDTO.getTransportType(), ticketDTO.getNumTickets(),
						ticketDTO.getPrice(), ticketDTO.getPrice() * ticketDTO.getNumTickets(), user));
	}
	
	private TicketDTO convertToTicketDTO(Ticket ticket) {
		return new TicketDTO(ticket.getId(), ticket.getUser().getName(), ticket.getCity(), ticket.getTransportType(),
				ticket.getNumTickets(), ticket.getPrice(), ticket.getTotal());
	}

}
