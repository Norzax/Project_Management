package com.baoluangiang.project_management.exceptions;

import com.baoluangiang.project_management.models.ErrorDetails;
import org.springframework.http.ResponseEntity;

public interface IExceptionHandler {
    ResponseEntity<ErrorDetails> handleException(Exception exception);
}