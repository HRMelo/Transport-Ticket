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

import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.repository.TransportRepository;

class TransportServiceTest {
	
	@InjectMocks
	private TransportServiceImpl transportService;
	
	@Mock
	private TransportRepository transportRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAllTransport() {
		//given
		List<Transport> transportListExpected = new ArrayList<>();
		transportListExpected.add(new Transport("Car", 1));
		given(this.transportRepository.findAll()).willReturn(transportListExpected);
		
		//when
		List<Transport> transportList = transportService.getAllTransports();
		
		//then
		verify(this.transportRepository).findAll();
		assertEquals(1, transportList.size());
	}

}
