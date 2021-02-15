package com.hiberus.pricegenerator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.pricegenerator.domain.Transport;
import com.hiberus.pricegenerator.repository.TransportRepository;

@Service
public class TransportServiceImpl implements TransportService {
	
	@Autowired
	private TransportRepository transportRepo;

	@Override
	public List<Transport> getAllTransports() {
		return transportRepo.findAll();
	}

}
