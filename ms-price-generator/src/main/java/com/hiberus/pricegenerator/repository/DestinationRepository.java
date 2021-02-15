package com.hiberus.pricegenerator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiberus.pricegenerator.domain.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>{

	Optional<Destination> findByCity(String destination);
}
