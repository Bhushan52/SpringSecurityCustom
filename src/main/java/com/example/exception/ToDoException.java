package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ToDoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4304687175968698347L;

	public ToDoException(String arg0) {
		super(arg0);
	}

	
}
