package com.example.demo.exception;

public class PositionNotFoundException extends RuntimeException {
	public PositionNotFoundException(String message) {
		super(message);
	}
}
