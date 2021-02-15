package com.hiberus.pricegenerator.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.dto.PriceGeneratorDTO;
import com.hiberus.pricegenerator.exceptions.PriceGeneratorException;
import com.hiberus.pricegenerator.service.PriceCalculatorService;

@SpringBootTest
@AutoConfigureMockMvc
class PriceCalculatorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PriceCalculatorService priceCalculatorService;

	@Test
	void testPriceCalculator() throws Exception {
		PriceGeneratorDTO priceGeneratorDTO = new PriceGeneratorDTO(new Destination("London", 1.0), new Transport("Car", 1), 1.0);
		when(priceCalculatorService.calculatePrice("London", "Car")).thenReturn(priceGeneratorDTO);
		
		mvc.perform(get("/price").param("destination", "London").param("transport", "Car"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.price", equalTo(priceGeneratorDTO.getPrice())));
	}
	
	@Test
	void testPriceCalculatorNotFound() throws Exception {
		doThrow(PriceGeneratorException.class).when(this.priceCalculatorService).calculatePrice("London", "Car");
		
		mvc.perform(get("/price").param("destination", "London").param("transport", "Car"))
			.andExpect(status().isNotFound());
	}

}
