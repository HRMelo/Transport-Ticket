package com.hiberus.pricegenerator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.dto.PriceGeneratorDTO;
import com.hiberus.pricegenerator.exceptions.PriceGeneratorException;
import com.hiberus.pricegenerator.repository.DestinationRepository;
import com.hiberus.pricegenerator.repository.TransportRepository;

@Service
public class PriceCalculatorServiceImpl implements PriceCalculatorService {
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	@Autowired
	private TransportRepository transportRepository;

	@Override
	public PriceGeneratorDTO calculatePrice(String city, String transportType) throws PriceGeneratorException {
		Optional<Destination> destination = destinationRepository.findByCity(city);
		Optional<Transport> transport = transportRepository.findByTransportType(transportType);
		
		if(!destination.isPresent()) {
			throw new PriceGeneratorException("Destination Not Found");
		}
		if(!transport.isPresent()) {
			throw new PriceGeneratorException("Transport Not Found");
		}
		return new PriceGeneratorDTO(destination.get(), transport.get(), destination.get().getPrice() * transport.get().getCost());
	}

}
