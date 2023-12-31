package com.baoluangiang.project_management.exceptions;

import com.baoluangiang.project_management.models.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(UserNotFoundException.class)
//
//    public ResponseEntity <ErrorDetails> userNotFoundException(UserNotFoundException ex) {
//
//        ErrorDetails errorModel = new ErrorDetails(0, ex.getMessage());
//
//        return new ResponseEntity < ErrorDetails > (errorModel, HttpStatus.NOT_FOUND);
//
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {

        ErrorDetails errorModel = new ErrorDetails(0, ex.getMessage());
        return new ResponseEntity <> (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}