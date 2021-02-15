package com.hiberus.manager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DestinationDTO {

	private Long id;

	private final String city;

	private final double price;

}
