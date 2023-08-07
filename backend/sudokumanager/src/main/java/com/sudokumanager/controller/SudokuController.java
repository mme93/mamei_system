package com.sudokumanager.controller;

import com.sudokumanager.model.dto.SudokuLevelRequest;
import com.sudokumanager.service.SudokuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    private final SudokuService sudokuService;

    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    @PostMapping("/load")
    public ResponseEntity loadSudoku(@RequestBody SudokuLevelRequest levelRequest) {
        try{
            sudokuService.loadSudoku(levelRequest.getUsername(), levelRequest.getDifficultyLevel(), levelRequest.getLevel());
            return new ResponseEntity(HttpStatus.OK);
        }catch (NoSuchElementException e){
            System.err.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
