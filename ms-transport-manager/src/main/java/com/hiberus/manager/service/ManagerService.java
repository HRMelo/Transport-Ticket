package com.hiberus.manager.service;

import java.util.Optional;

import com.hiberus.manager.dto.TicketDTO;

public interface ManagerService {

	public Optional<TicketDTO> searchRoute(String city, String transportType) throws Exception;
	
	public TicketDTO createTicket(TicketDTO ticketDTO);
	
	public Optional<TicketDTO> findTicketDTO(Long id);
}
