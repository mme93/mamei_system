package com.gamesmanager.game.sudoku.service;

import com.gamesmanager.game.sudoku.model.dto.*;
import com.gamesmanager.game.sudoku.model.entities.DifficultyLevel;
import com.gamesmanager.game.sudoku.model.entities.SudokuEntity;
import com.gamesmanager.game.sudoku.repository.SudokuRepository;
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

    private List<SudokuBlock> generateSudokuBlocks(String[] sudoku81Array) {
        List<SudokuBlock> sudokuBlocks = new ArrayList<>();
        int rows = 3;
        int cols = 3;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String[] sudokuBlockArray = new String[9];
                for (int k = 0; k < 3; k++) {
                    for (int m = 0; m < 3; m++) {
                        sudokuBlockArray[(k * 3) + m] = sudoku81Array[(i * 27) + (j * 3) + (k * 9) + m];
                    }
                }
                sudokuBlocks.add(new SudokuBlock(sudokuBlockArray,(i*3)+j+1));
            }
        }
        return sudokuBlocks;
    }

    private List<SudokuRows> generateSudokuRows(String[] sudoku81Array) {
        List<SudokuRows> sudokuRows = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String[] sudoku9Array = new String[9];

            for (int j = 0; j < 9; j++) {
                sudoku9Array[j] = sudoku81Array[(i * 9) + j];
            }
            sudokuRows.add(new SudokuRows(sudoku9Array));
        }
        return sudokuRows;
    }

    private List<SudokuColumns> generateSudokuColumns(String[] sudoku81Array) {
        List<SudokuColumns> sudokuColumns = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String[] sudoku9Array = new String[9];

            for (int j = 0; j < 9; j++) {
                sudoku9Array[j] = sudoku81Array[(j * 9) + i];
            }
            sudokuColumns.add(new SudokuColumns(sudoku9Array));
        }
        return sudokuColumns;
    }

    private SudokuGrid generateSudokuGrid(String sudoku81) {
        String[] sudoku81Array = sudoku81.split(", ");
        List<SudokuRows> sudokuRows = generateSudokuRows(sudoku81Array);
        List<SudokuColumns> sudokuColumns = generateSudokuColumns(sudoku81Array);
        List<SudokuBlock> sudokuBlocks = generateSudokuBlocks(sudoku81Array);
        return new SudokuGrid(sudokuRows, sudokuColumns, sudokuBlocks);
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
