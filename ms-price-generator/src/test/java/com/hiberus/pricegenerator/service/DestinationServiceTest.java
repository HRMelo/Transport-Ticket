package com.hiberus.pricegenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.repository.DestinationRepository;

class DestinationServiceTest {
	
	@InjectMocks
	private DestinationServiceImpl destinatioService;
	
	@Mock
	private DestinationRepository destinationRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAllDestination() {
		//given
		List<Destination> destinationListExpected = new ArrayList<>();
		destinationListExpected.add(new Destination("London", 1.0));
		given(this.destinationRepository.findAll()).willReturn(destinationListExpected);
		
		//when
		List<Destination> destinationList = destinatioService.getAllDestination();
		
		//then
		verify(this.destinationRepository).findAll();
		assertEquals(1, destinationList.size());
	}

}
