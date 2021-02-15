package com.hiberus.pricegenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.dto.PriceGeneratorDTO;
import com.hiberus.pricegenerator.exceptions.PriceGeneratorException;
import com.hiberus.pricegenerator.repository.DestinationRepository;
import com.hiberus.pricegenerator.repository.TransportRepository;

class PriceCalculatorServiceTest {
	
	@InjectMocks
	private PriceCalculatorServiceImpl priceCalculatorService;
	
	@Mock
	private DestinationRepository destinationRepo;
	
	@Mock
	private TransportRepository transportRepo;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCalculatePrice() throws PriceGeneratorException {
		Optional<Destination> destination = Optional.of(new Destination("London", 1.0));
		Optional<Transport> transport = Optional.of(new Transport("Car", 1));
		when(destinationRepo.findByCity("London")).thenReturn(destination);
		when(transportRepo.findByTransportType("Car")).thenReturn(transport);
		
		PriceGeneratorDTO priceGeneratorDTO = priceCalculatorService.calculatePrice("London", "Car");
		
		verify(destinationRepo).findByCity("London");
		verify(transportRepo).findByTransportType("Car");
		assertEquals("London", priceGeneratorDTO.getDestination().getCity());
		assertEquals("Car", priceGeneratorDTO.getTransport().getTransportType());
		assertEquals(1, priceGeneratorDTO.getPrice());
	}

}
