package com.hiberus.pricegenerator.dto;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.domain.Transport;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PriceGeneratorDTO {

	private final Destination destination;
	
	private final Transport transport;
	
	private final double price;
}
