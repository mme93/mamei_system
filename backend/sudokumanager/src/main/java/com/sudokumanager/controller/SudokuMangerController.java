package com.sudokumanager.controller;

import com.sudokumanager.service.SudokuManagerService;
import com.sudokumanager.service.SudokuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sudokumanager")
public class SudokuMangerController {

    private final SudokuService sudokuService;
    private final SudokuManagerService sudokuManagerService;


    public SudokuMangerController(SudokuService sudokuService, SudokuManagerService sudokuManagerService) {
        this.sudokuService = sudokuService;
        this.sudokuManagerService = sudokuManagerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createSudoku() {
        if (sudokuManagerService.createSudoku()) {
            return new ResponseEntity(true,HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.CONFLICT);
    }

}
