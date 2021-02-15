package com.hiberus.manager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TransportDTO {
	
	private Long id;

	private final String transportType;

	private final Integer cost;
}
