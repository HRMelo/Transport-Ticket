package com.hiberus.pricegenerator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.pricegenerator.domain.Destination;
import com.hiberus.pricegenerator.repository.DestinationRepository;

@Service
public class DestinationServiceImpl implements DestinationService {
	
	@Autowired
	private DestinationRepository destinationRepo;

	@Override
	public List<Destination> getAllDestination() {
		return (List<Destination>) destinationRepo.findAll();
	}

}
