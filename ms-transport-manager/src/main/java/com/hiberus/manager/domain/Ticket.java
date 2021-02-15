package com.hiberus.manager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6696515423506661647L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String city;
	
	@Column(name = "TRANSPORT_TYPE")
	private String transportType;
	
	@Column(name = "NUM_TICKETS")
	private int numTickets;
	
	private double price;
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public Ticket(String city, String transportType, int numTickets, double price, double total, User user) {
		this.city = city;
		this.transportType = transportType;
		this.numTickets = numTickets;
		this.price = price;
		this.total = total;
		this.user = user;
	}
}
