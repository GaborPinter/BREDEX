package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Position;
import com.example.demo.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	private PositionRepository positionRepository;

	public Position createPosition(Position position) {
		return positionRepository.save(position);
	}

	public List<Position> searchPositions(String keyword, String location) {
		return positionRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(keyword, location);
	}

	public Optional<Position> getPositionById(UUID id) {
		return positionRepository.findById(id);
	}
}
