package com.example.demo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void run(String... args) throws Exception {
		clientRepository.save(new Client("John Doe", "john.doe@example.com", UUID.randomUUID()
				.toString()));
		clientRepository.save(new Client("Jane Doe", "jane.doe@example.com", UUID.randomUUID()
				.toString()));
	}
}
