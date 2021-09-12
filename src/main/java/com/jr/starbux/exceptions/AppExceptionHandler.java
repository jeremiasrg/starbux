package com.jr.starbux.exceptions;

import com.jr.starbux.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request){
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErrorMessage error = new ErrorMessage(new Date(), errorDescription);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={ObjectNotFoundException.class})
    public ResponseEntity<Object> handleObjectNotFoundException(Exception e, WebRequest request){
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErrorMessage error = new ErrorMessage(new Date(), errorDescription);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
