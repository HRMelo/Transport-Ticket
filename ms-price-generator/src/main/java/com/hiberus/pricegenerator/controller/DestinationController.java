package com.hiberus.pricegenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.service.DestinationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/destination")
public class DestinationController {
	
	@Autowired
	private DestinationService destinationService;

	@Operation(summary = "Get all destinations")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "List with all destination",
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Destination.class))})
	})
	@GetMapping
	public List<Destination> getAllDestination(){
		return destinationService.getAllDestination();
	}
}
