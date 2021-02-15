package com.hiberus.manager.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hiberus.manager.dto.DestinationDTO;
import com.hiberus.manager.dto.PriceGeneratorDTO;
import com.hiberus.manager.dto.TransportDTO;

@FeignClient(name = "pricegenerator", url = "http://localhost:8000/price")
public interface PriceGeneratorClient {
	
	@GetMapping("/destination")
	public List<DestinationDTO> getAllDestination();

	@GetMapping("/transport")
	public List<TransportDTO> getAllTransports();
	
	@GetMapping("/price")
	public PriceGeneratorDTO priceCalculator(@RequestParam("destination") String city,
			@RequestParam("transport") String transportType) throws Exception;
}
