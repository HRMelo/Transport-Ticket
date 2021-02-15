package com.hiberus.pricegenerator.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.service.TransportService;

@SpringBootTest
@AutoConfigureMockMvc
class TransportControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TransportService transportService;

	@Test
	void testGetAllTransport() throws Exception {
		List<Transport> transports = new ArrayList<>();
		transports.add(new Transport("Car", 1));
		transports.add(new Transport("Bus", 1));
		given(transportService.getAllTransports()).willReturn(transports);
		
		mvc.perform(get("/transport")).andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].transportType", equalTo(transports.get(0).getTransportType())))
			.andExpect(jsonPath("$[0].cost", equalTo(transports.get(0).getCost())))
			.andExpect(jsonPath("$[1].transportType", equalTo(transports.get(1).getTransportType())))
			.andExpect(jsonPath("$[1].cost", equalTo(transports.get(1).getCost())));;
	}

}
