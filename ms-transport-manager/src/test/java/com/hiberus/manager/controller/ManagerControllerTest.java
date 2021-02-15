package com.hiberus.manager.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.manager.dto.TicketDTO;
import com.hiberus.manager.service.ManagerService;

@SpringBootTest
@AutoConfigureMockMvc
class ManagerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ManagerService managerService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testSearch() throws Exception {
		Optional<TicketDTO> ticket = Optional.of(new TicketDTO(null, null, "London", "Car", 1, 1.0, 1.0));
		when(this.managerService.searchRoute("London", "Car")).thenReturn(ticket);
		
		
		mvc.perform(get("/book/").param("city", "London").param("transportType", "Car")).andExpect(status().isOk())
			.andExpect(jsonPath("$.city", equalTo(ticket.get().getCity())))
			.andExpect(jsonPath("$.transportType", equalTo(ticket.get().getTransportType())));
		
		verify(managerService).searchRoute("London", "Car");
	}
	
	@Test
	void testCreateTicket() throws Exception {
		TicketDTO ticketDTO = new TicketDTO(1L, "John", "London", "Car", 1, 1.0, 1.0);
		when(this.managerService.createTicket(ticketDTO)).thenReturn(ticketDTO);
		
		
		mvc.perform(post("/book/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(ticketDTO)))
			.andExpect(status().isCreated());
		
		verify(this.managerService).createTicket(ticketDTO);
	}

}
