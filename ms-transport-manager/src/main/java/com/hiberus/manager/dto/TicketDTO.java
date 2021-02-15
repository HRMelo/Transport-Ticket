package com.hiberus.manager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TicketDTO {
	
	private final Long id;
	
	private final String user;

	private final String city;

	private final String transportType;

	private final int numTickets;

	private final double price;

	private final double total;

}
