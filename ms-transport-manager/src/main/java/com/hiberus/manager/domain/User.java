package com.hiberus.manager.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7198548461813286649L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Ticket> tickets;

	public User(String name) {
		this.name = name;
	}
}
