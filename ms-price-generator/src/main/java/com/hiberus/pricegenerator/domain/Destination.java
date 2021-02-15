package com.hiberus.pricegenerator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Destination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String city;
	
	private double price;

	public Destination() {
	}

	public Destination(String city, double price) {
		this.city = city;
		this.price = price;
	}
	
}
