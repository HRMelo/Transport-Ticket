package com.hiberus.pricegenerator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiberus.pricegenerator.domain.Transport;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long>{

	Optional<Transport> findByTransportType(String transport);
}
