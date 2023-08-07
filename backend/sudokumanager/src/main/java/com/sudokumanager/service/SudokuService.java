package com.sudokumanager.service;

import com.sudokumanager.model.dto.*;
import com.sudokumanager.model.entities.DifficultyLevel;
import com.sudokumanager.model.entities.SudokuEntity;
import com.sudokumanager.repository.SudokuRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SudokuService {

    private final SudokuRepository sudokuRepository;

    @Autowired
    public SudokuService(SudokuRepository sudokuRepository) {
        this.sudokuRepository = sudokuRepository;
    }

    private List<SudokuBlock> generateSudokuBlocks() {

        return null;
    }

    private void generateSudokuRows(String[] sudoku81Array) {
        List<SudokuRows> sudokuRows = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String[] sudoku9Array = new String[9];

            for (int j = 0; j < 9; j++) {
                sudoku9Array[j]=sudoku81Array[(i*9)+j];
            }
            sudokuRows.add(new SudokuRows(sudoku9Array));
        }
    }

    private void generateSudokuColumns(String[] sudoku81Array) {
        List<SudokuColumns> sudokuColumns = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String[] sudoku9Array = new String[9];

            for (int j = 0; j < 9; j++) {
                sudoku9Array[j]=sudoku81Array[(j*9)+i];
            }
            sudokuColumns.add(new SudokuColumns(sudoku9Array));
        }
    }

    private SudokuGrid generateSudokuGrid(String sudoku81) {
        String[] sudoku81Array = sudoku81.split(", ");
        generateSudokuRows(sudoku81Array);
        generateSudokuColumns(sudoku81Array);
        return new SudokuGrid(generateSudokuBlocks());
    }


    public SudokuLevel loadSudoku(String username, DifficultyLevel difficultyLevel, int level) {
        SudokuEntity result = null;
        for (SudokuEntity entity : sudokuRepository.findByUsername(username)) {
            if (entity.getDifficultyLevel() == difficultyLevel && entity.getLevel() == level) {
                result = entity;
                break;
            }
        }
        if (result == null) {
            throw new NotFoundException();
        }
        return new SudokuLevel(username, difficultyLevel, level, generateSudokuGrid(result.getSudoku81()));

    }
}
