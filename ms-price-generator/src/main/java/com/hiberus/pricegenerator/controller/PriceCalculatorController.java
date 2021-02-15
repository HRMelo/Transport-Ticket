package com.hiberus.pricegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.dto.PriceGeneratorDTO;
import com.hiberus.pricegenerator.exceptions.PriceGeneratorException;
import com.hiberus.pricegenerator.service.PriceCalculatorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/price")
public class PriceCalculatorController {
	
	@Autowired
	private PriceCalculatorService priceCalculatorService;

	@Operation(summary = "Calculate price")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Calculate price by transport and destination",
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Destination.class))}),
			@ApiResponse(
					responseCode = "404",
					content = @Content)
	})
	@GetMapping
	public ResponseEntity<PriceGeneratorDTO> priceCalculator(
			@Parameter(description = "destination") @RequestParam("destination") String city,
			@Parameter(description = "transport") @RequestParam("transport") String transportType) throws PriceGeneratorException {
		return ResponseEntity.ok(priceCalculatorService.calculatePrice(city, transportType));
	}
}
