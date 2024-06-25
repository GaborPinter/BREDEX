package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Operation(summary = "Register a new client")
	@PostMapping
	public ResponseEntity<String> registerClient(
			@Parameter(description = "Client's name", required = true) @RequestParam @NotBlank @Size(max = 100) String name,
			@Parameter(description = "Client's email", required = true) @RequestParam @NotBlank @Email @Pattern(regexp = ".+@.+\\..+") String email) {
		if (clientService.findByEmail(email)
				.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		Client client = new Client(name, email, UUID.randomUUID()
				.toString());
		Client savedClient = clientService.registerClient(client);
		return new ResponseEntity<>(savedClient.getApiKey(), HttpStatus.CREATED);
	}
}
