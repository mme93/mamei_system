package com.apigateway.api.sudokumanager.controller;

import com.apigateway.api.sudokumanager.model.dto.APIDifficultyLevel;
import com.apigateway.api.sudokumanager.model.dto.APISudokuLevelRequest;
import com.apigateway.api.sudokumanager.service.APISudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sudoku")
public class APISudokuManagerController {

    private final APISudokuService APISudokuService;
    @Autowired
    public APISudokuManagerController(APISudokuService APISudokuService) {
        this.APISudokuService = APISudokuService;
    }

    @PostMapping("/create")
    public ResponseEntity createSudoku() {
        if (APISudokuService.generateSudoku().block()) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @PostMapping("/load/{level}/{difficultyLevel}")
    public ResponseEntity<Object> loadSudoku(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String difficultyLevel, @PathVariable String level) {
        String username = "Markus";
        Object sudokuLevel = APISudokuService.loadSudoku(new APISudokuLevelRequest(username, APIDifficultyLevel.valueOf(difficultyLevel), Integer.valueOf(level)));
        if (sudokuLevel != null) {
            return new ResponseEntity(sudokuLevel, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @PostMapping("/load/levelList")
    public ResponseEntity<Object> loadLevelList(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = "Markus";
        Object sudokuLevelList = APISudokuService.loadSudokuLevelList(username);
        if (sudokuLevelList != null) {
            return new ResponseEntity(sudokuLevelList, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

}
