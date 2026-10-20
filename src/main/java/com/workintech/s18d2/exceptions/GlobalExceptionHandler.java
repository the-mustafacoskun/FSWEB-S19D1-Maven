package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<PlantErrorResponse> handleException(PlantException plantException) {
        PlantErrorResponse plantErrorResponse = new PlantErrorResponse(plantException.getHttpStatus().value(),plantException.getMessage(),System.currentTimeMillis());
        log.error(plantErrorResponse.toString());
        return new ResponseEntity<>(plantErrorResponse,plantException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<PlantErrorResponse> handleException(Exception exception) {
        PlantErrorResponse plantErrorResponse = new PlantErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        log.error(plantErrorResponse.toString());
        return new ResponseEntity<>(plantErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
