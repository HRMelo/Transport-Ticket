package com.hiberus.pricegenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.service.TransportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/transport")
public class TransportController {

	@Autowired
	private TransportService transportService;
	
	@Operation(summary = "Get all transports")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "List with all transports",
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Transport.class))})
	})
	@GetMapping
	public List<Transport> getAllTransports(){
		return transportService.getAllTransports();
	}
}
