package com.example.webstorecartservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8298007324725365626L;

	public CartNotFoundException(String message) {
		super(message);
	}
}
