package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
	Optional<Client> findByEmail(String email);

	Optional<Client> findByApiKey(String apiKey);

}
