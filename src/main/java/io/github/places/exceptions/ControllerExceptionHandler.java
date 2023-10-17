package io.github.places.exceptions;

import io.github.places.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Lugar já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity handleUserNotFound(PlaceNotFoundException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }
}
