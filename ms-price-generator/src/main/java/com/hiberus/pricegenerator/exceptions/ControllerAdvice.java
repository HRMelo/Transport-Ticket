package com.hiberus.pricegenerator.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(PriceGeneratorException.class)
	public ResponseEntity<Object> handleNotFoundException(PriceGeneratorException ex, WebRequest request) {
		Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
