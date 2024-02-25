package com.apigateway.api.micro_services.sudokumanager.controller;

import com.apigateway.api.micro_services.sudokumanager.model.dto.APIDifficultyLevel;
import com.apigateway.api.micro_services.sudokumanager.model.dto.APISudokuLevelRequest;
import com.apigateway.api.micro_services.sudokumanager.service.APISudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling Sudoku-related API endpoints.
 */
@RestController
@RequestMapping("/api/sudoku")
public class APISudokuManagerController {

    private final APISudokuService APISudokuService;

    @Autowired
    public APISudokuManagerController(APISudokuService APISudokuService) {
        this.APISudokuService = APISudokuService;
    }

    /**
     * Endpoint to create a new Sudoku puzzle.
     *
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping("/create")
    public ResponseEntity createSudoku() {
        if (APISudokuService.generateSudoku().block()) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    /**
     * Endpoint to load a Sudoku puzzle.
     *
     * @param token           Authorization token.
     * @param difficultyLevel Difficulty level of the Sudoku puzzle.
     * @param level           Level of the Sudoku puzzle.
     * @return ResponseEntity containing the loaded Sudoku puzzle.
     */
    @PostMapping("/load/{level}/{difficultyLevel}")
    public ResponseEntity<Object> loadSudoku(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String difficultyLevel, @PathVariable String level) {
        String username = "Markus";
        Object sudokuLevel = APISudokuService.loadSudoku(new APISudokuLevelRequest(username, APIDifficultyLevel.valueOf(difficultyLevel), Integer.valueOf(level)));
        if (sudokuLevel != null) {
            return new ResponseEntity(sudokuLevel, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    /**
     * Endpoint to load a list of available Sudoku puzzle levels.
     *
     * @param token Authorization token.
     * @return ResponseEntity containing the list of available Sudoku puzzle levels.
     */
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
