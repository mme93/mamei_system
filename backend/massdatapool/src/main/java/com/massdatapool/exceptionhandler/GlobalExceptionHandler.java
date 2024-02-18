package com.massdatapool.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger LOGGER = LoggerFactory.getLogger("general.exception.error");

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        LOGGER.error("An exception was thrown:", ex);
        return new ResponseEntity<>("An exception was thrown: "+ex.fillInStackTrace()+" \n For more Information look up into the log-files.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
