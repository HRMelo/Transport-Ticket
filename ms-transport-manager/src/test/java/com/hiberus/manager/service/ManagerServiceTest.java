package com.hiberus.manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hiberus.manager.client.PriceGeneratorClient;
import com.hiberus.manager.domain.Ticket;
import com.hiberus.manager.domain.User;
import com.hiberus.manager.dto.DestinationDTO;
import com.hiberus.manager.dto.PriceGeneratorDTO;
import com.hiberus.manager.dto.TicketDTO;
import com.hiberus.manager.dto.TransportDTO;
import com.hiberus.manager.repository.TicketRepository;
import com.hiberus.manager.repository.UserRepository;

class ManagerServiceTest {

	@InjectMocks
	private ManagerServiceImpl managerService;

	@Mock
	private PriceGeneratorClient priceGeneratorClient;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TicketRepository ticketRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSearchRoute() throws Exception {
		PriceGeneratorDTO price = new PriceGeneratorDTO(new DestinationDTO("London", 1.0), new TransportDTO("Car", 1), 1.0);
		TicketDTO ticketDTOExpected = new TicketDTO(null, "", "London", "Car", 1, 1.0, 1.0);
		when(priceGeneratorClient.priceCalculator("London", "Car")).thenReturn(price);
		
		Optional<TicketDTO> ticketDTO = managerService.searchRoute("London", "Car");
		
		verify(priceGeneratorClient).priceCalculator(anyString(), anyString());
		assertEquals(ticketDTOExpected.getUser(), ticketDTO.get().getUser());
		assertEquals(ticketDTOExpected.getCity(), ticketDTO.get().getCity());
		assertEquals(ticketDTOExpected.getTransportType(), ticketDTO.get().getTransportType());
		assertEquals(ticketDTOExpected.getNumTickets(), ticketDTO.get().getNumTickets());
		assertEquals(ticketDTOExpected.getPrice(), ticketDTO.get().getPrice());
		assertEquals(ticketDTOExpected.getTotal(), ticketDTO.get().getTotal());
	}

	@Test
	void testCreateTicket() {
		Optional<User> user = Optional.of(new User("John"));
		when(userRepository.findByName("John")).thenReturn(user);
		
		TicketDTO ticketDTOExpected = new TicketDTO(null, "John", "London", "Car", 1, 1.0, 1.0);
		Ticket ticket = new Ticket("London", "Car", 1, 1.0, 1.0, user.get());
		when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
		
		TicketDTO ticketDTOActual = managerService.createTicket(ticketDTOExpected);
		
		verify(userRepository).findByName(anyString());
		verify(ticketRepository).save(any(Ticket.class));
		assertEquals(ticketDTOExpected.getUser(), ticketDTOActual.getUser());
		assertEquals(ticketDTOExpected.getCity(), ticketDTOActual.getCity());
		assertEquals(ticketDTOExpected.getTransportType(), ticketDTOActual.getTransportType());
		assertEquals(ticketDTOExpected.getNumTickets(), ticketDTOActual.getNumTickets());
		assertEquals(ticketDTOExpected.getPrice(), ticketDTOActual.getPrice());
	}

}
