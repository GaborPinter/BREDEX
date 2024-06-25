package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

	List<Position> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);

	Optional<Position> findById(UUID id);
}
