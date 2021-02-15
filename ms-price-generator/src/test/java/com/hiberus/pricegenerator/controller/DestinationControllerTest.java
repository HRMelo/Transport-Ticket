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

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.service.DestinationService;

@SpringBootTest
@AutoConfigureMockMvc
class DestinationControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DestinationService destinationService;

	@Test
	void testGetAllDestination() throws Exception {
		List<Destination> destinations = new ArrayList<>();
		destinations.add(new Destination("London", 1.0));
		destinations.add(new Destination("Liverpool", 2.0));
		given(destinationService.getAllDestination()).willReturn(destinations);

		mvc.perform(get("/destination")).andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].city", equalTo(destinations.get(0).getCity())))
			.andExpect(jsonPath("$[0].price", equalTo(destinations.get(0).getPrice())))
			.andExpect(jsonPath("$[1].city", equalTo(destinations.get(1).getCity())))
			.andExpect(jsonPath("$[1].price", equalTo(destinations.get(1).getPrice())));

	}

}
