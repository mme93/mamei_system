package com.apigateway.api.sudokumanager.controller;

import com.apigateway.api.sudokumanager.service.APISudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/sudoku")
public class APISudokuManagerController {

    private final APISudokuService APISudokuService;

    @Autowired
    public APISudokuManagerController(APISudokuService APISudokuService) {
        this.APISudokuService = APISudokuService;
    }

    @PostMapping("/create")
    public ResponseEntity createSudoku(){
        if (APISudokuService.generateSudoku().block()) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

}
