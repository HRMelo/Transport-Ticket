package com.hiberus.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.manager.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
