package com.grievance_management.config;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleSql(DataAccessException ex) {
        return ResponseEntity.badRequest().body(ex.getMostSpecificCause().getMessage());
    }
}
