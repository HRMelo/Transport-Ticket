package com.hiberus.manager.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.manager.dto.TicketDTO;
import com.hiberus.manager.service.ManagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/book")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	@Operation(summary = "Get a Ticket by city and transport")
    @ApiResponses(value = {
            @ApiResponse(
        		responseCode = "200", 
        		description = "Found Ticket", 
        		content = { @Content(
    				mediaType = "application/json", 
    				schema = @Schema(implementation = TicketDTO.class)
				)
            }),
            @ApiResponse(
        		responseCode = "404", 
        		description = "Ticket not found", 
        		content = @Content)
    })
	@GetMapping("/")
	public ResponseEntity<TicketDTO> searchRoute(
			@Parameter(description = "City") @RequestParam("city") String city, 
			@Parameter(description = "Transport") @RequestParam("transportType") String transportType) throws Exception {
		return ResponseEntity.of(this.managerService.searchRoute(city, transportType));
	}
	
	@Operation(summary = "Get a Ticket by its ID")
    @ApiResponses(value = {
            @ApiResponse(
        		responseCode = "200", 
        		description = "Found Ticket", 
        		content = { @Content(
    				mediaType = "application/json", 
    				schema = @Schema(implementation = TicketDTO.class)
				)
            }),
            @ApiResponse(
        		responseCode = "404", 
        		description = "Ticket not found", 
        		content = @Content)
    })
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getTicket(
			@Parameter(description = "TicketId to be found") @PathVariable Long id) {
		return ResponseEntity.of(this.managerService.findTicketDTO(id));
	}
	
	 @Operation(summary = "Create a new ticket")
	    @ApiResponses(value = {
	            @ApiResponse(
	        		responseCode = "201", 
	        		description = "Created ticket", 
	        		content = { @Content(
	    				mediaType = "application/json", 
	    				schema = @Schema(implementation = TicketDTO.class))
	            })
	    })
	@PostMapping
	public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
		TicketDTO createdTicket = this.managerService.createTicket(ticketDTO);
		URI location = fromCurrentRequest().path("/").build(createdTicket.getId());
		return ResponseEntity.created(location).body(createdTicket);
	}
}
