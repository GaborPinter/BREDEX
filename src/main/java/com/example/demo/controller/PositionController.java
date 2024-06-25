package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.InvalidApiKeyException;
import com.example.demo.exception.PositionNotFoundException;
import com.example.demo.model.Position;
import com.example.demo.service.ClientService;
import com.example.demo.service.PositionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/position")
@Validated
public class PositionController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private PositionService positionService;

	@Operation(summary = "Create a new position")
	@PostMapping
	public ResponseEntity<String> createPosition(
			@Parameter(description = "Job title", required = true) @RequestParam("title") @Size(max = 50) String title,
			@Parameter(description = "Job location", required = true) @RequestParam("location") @Size(max = 50) String location,
			@Parameter(description = "API key", required = true) @RequestParam("apiKey") String apiKey) {
		if (!clientService.isValidApiKey(apiKey)) {
			throw new InvalidApiKeyException("Invalid API key");
		}

		Position position = new Position(title, location);
		Position savedPosition = positionService.createPosition(position);
		String positionUrl = "http://localhost:8080/position/" + savedPosition.getId() + "?apiKey=" + apiKey;
		return new ResponseEntity<>(position.getTitle() + ", " + position.getLocation() + " - " + positionUrl,
				HttpStatus.CREATED);
	}

	@Operation(summary = "Search for positions")
	@GetMapping("/search")
	public ResponseEntity<List<String>> searchPositions(
			@Parameter(description = "API key", required = true) @RequestParam("apiKey") String apiKey,
			@Parameter(description = "Job title", required = true) @RequestParam("title") @Size(max = 50) String title,
			@Parameter(description = "Job location", required = true) @RequestParam("location") @Size(max = 50) String location) {
		if (!clientService.isValidApiKey(apiKey)) {
			throw new InvalidApiKeyException("Invalid API key");
		}

		List<Position> positions = positionService.searchPositions(title, location);
		List<String> positionUrls = positions.stream()
				.map(position -> position.getTitle() + ", " + position.getLocation()
						+ " - http://localhost:8080/position/" + position.getId() + "?apiKey=" + apiKey)
				.collect(Collectors.toList());

		return new ResponseEntity<>(positionUrls, HttpStatus.OK);
	}

	@Operation(summary = "Get position by ID")
	@GetMapping("/{id}")
	public ResponseEntity<String> getPositionById(
			@Parameter(description = "Position ID", required = true) @PathVariable UUID id,
			@Parameter(description = "API key", required = true) @RequestParam("apiKey") String apiKey) {
		if (!clientService.isValidApiKey(apiKey)) {
			throw new InvalidApiKeyException("Invalid API key");
		}

		Optional<Position> optionalPosition = positionService.getPositionById(id);
		if (optionalPosition.isEmpty()) {
			throw new PositionNotFoundException("Position not found");
		}

		String positionUrl = "http://localhost:8080/position/" + id + "?apiKey=" + apiKey;
		return new ResponseEntity<>(optionalPosition.get()
				.getTitle() + ", "
				+ optionalPosition.get()
						.getLocation()
				+ " - " + positionUrl, HttpStatus.OK);
	}
}
