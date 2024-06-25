package com.example.demo.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@NotBlank
	@Size(max = 100)
	private String name;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	private String apiKey;

	public Client() {
	}

	public Client(@NotBlank @Size(max = 100) String name, @NotBlank @Email String email, String apiKey) {
		this.name = name;
		this.email = email;
		this.apiKey = apiKey;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
