package com.steliana.springtaskuser.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFound.class)
    protected ResponseEntity handleException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
