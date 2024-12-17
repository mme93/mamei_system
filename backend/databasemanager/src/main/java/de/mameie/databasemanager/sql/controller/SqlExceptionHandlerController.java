package de.mameie.databasemanager.sql.controller;

import de.mameie.databasemanager.util.check.exception.ParamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

@ControllerAdvice
public class SqlExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        HttpStatus status = null;
        switch (ex.getClass().getSimpleName()) {
            case "SQLException", "IllegalArgumentException":
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case "NoResourceFoundException":
                status = HttpStatus.NOT_FOUND;
                break;
            case "ParameterException":
                status = HttpStatus.BAD_REQUEST;
                break;
        }
        ex.printStackTrace();
        return new ResponseEntity<>(ErrorResponse.builder(ex, status, ex.getMessage()).build(), status);
    }


}
