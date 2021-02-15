package com.hiberus.pricegenerator.service;

import com.hiberus.pricegenerator.dto.PriceGeneratorDTO;
import com.hiberus.pricegenerator.exceptions.PriceGeneratorException;

public interface PriceCalculatorService {

	public PriceGeneratorDTO calculatePrice(String city, String transportType) throws PriceGeneratorException;
}
