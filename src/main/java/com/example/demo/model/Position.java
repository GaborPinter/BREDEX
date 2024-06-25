package com.example.demo.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@NotEmpty
	@Size(max = 50)
	private String title;

	@NotEmpty
	@Size(max = 50)
	private String location;

	public Position() {
	}

	public Position(@NotEmpty @Size(max = 50) String title, @NotEmpty @Size(max = 50) String location) {
		this.title = title;
		this.location = location;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
