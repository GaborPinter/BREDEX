package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client registerClient(Client client) {
		return clientRepository.save(client);
	}

	public Optional<Client> findByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

	public boolean isValidApiKey(String apiKey) {
		Optional<Client> client = clientRepository.findByApiKey(apiKey);
		return client.isPresent();
	}
}
