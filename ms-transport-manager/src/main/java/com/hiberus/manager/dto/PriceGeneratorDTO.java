package com.hiberus.manager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PriceGeneratorDTO {

	private final DestinationDTO destination;
	
	private final TransportDTO transport;
	
	private final double price;
}
