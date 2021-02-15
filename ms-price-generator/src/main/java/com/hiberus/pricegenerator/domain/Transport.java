package com.hiberus.pricegenerator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TRANSPORT_TYPE")
	private String transportType;
	
	private Integer cost;

	public Transport() {
	}

	public Transport(String transportType, Integer cost) {
		this.transportType = transportType;
		this.cost = cost;
	}
}
