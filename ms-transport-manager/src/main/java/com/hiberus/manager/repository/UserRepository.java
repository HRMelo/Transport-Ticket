package com.hiberus.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.manager.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByName(String name);
}
