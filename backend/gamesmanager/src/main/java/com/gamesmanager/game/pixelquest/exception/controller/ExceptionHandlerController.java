package com.gamesmanager.game.pixelquest.exception.controller;


import com.gamesmanager.game.pixelquest.exception.model.ErrorResponse;
import com.gamesmanager.game.pixelquest.exception.PixelQuestInvalidLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(PixelQuestInvalidLoginException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoginException(PixelQuestInvalidLoginException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
