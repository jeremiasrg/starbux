package com.jr.starbux.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends Exception{

    public InvalidPasswordException() {
        super("Invalid Password");
    }
}
