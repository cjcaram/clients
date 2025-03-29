package com.cjcaram.clients.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ClientExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ClientExceptionHandler.class);

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ClientNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        String errorMessage = "Client not found with the parameters provided";
        error.put("error", errorMessage);
        logger.error(errorMessage, ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        logger.error("Validations errors", ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "An unexpected error occurred, please contact the system admin to get more information.");
        logger.error("Internal error", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> handleDatetimeFormatException(DateTimeParseException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Invalid DateTime format:", "The correct date time format is: yyyy-mm-dd");
        logger.error("Invalid DateTime format", ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleSqlException(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error:", "name or email already exist.");
        logger.error("Data integrity violation", ex);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
