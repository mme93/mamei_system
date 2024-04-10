package com.gamesmanager.game.sudoku.controller;

import com.sudokumanager.model.entities.SudokuLevelListItemEntity;
import com.sudokumanager.service.SudokuManagerService;
import com.sudokumanager.service.SudokuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.CONFLICT);
    }

    @PostMapping("/levelList/{username}")
    public ResponseEntity<List<SudokuLevelListItemEntity>> createLevelListByUser(@PathVariable String username) {
        List<SudokuLevelListItemEntity> result = sudokuManagerService.generateSudokuLevelList(username);
        if (result.size() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
