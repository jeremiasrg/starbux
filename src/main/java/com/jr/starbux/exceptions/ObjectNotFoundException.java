package com.jr.starbux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends Exception {


	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
        super("Object not found.");
    }
}
