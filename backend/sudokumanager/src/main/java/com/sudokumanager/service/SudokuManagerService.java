package com.sudokumanager.service;

import com.sudokumanager.model.entities.DifficultyLevel;
import com.sudokumanager.model.entities.SudokuEntity;
import com.sudokumanager.repository.SudokuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SudokuManagerService {

    private final SudokuRepository sudokuRepository;

    @Autowired
    public SudokuManagerService(SudokuRepository sudokuRepository) {
        this.sudokuRepository = sudokuRepository;
    }

    public boolean createSudoku() {
        try {
            String sudoku81 = "5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0, 0, 0, 9, 8, 0, 0, 0, 0, 6, 0, 8, 0, 0, 0, " +
                    "6, 0, 0, 0, 3, 4, 0, 0, 8, 0, 3, 0, 0, 1, 0, 6, 0, 0, 0, 0, 2, 8, 0, 7, 0, 0, 0, 2, 0, 0, 0, 6, 0, 0, 0, " +
                    "4, 1, 9, 0, 0, 5, 0, 0, 0, 0, 8, 0, 0, 7, 9";
            var sudokuEntity = SudokuEntity
                    .builder()
                    .level(0)
                    .processingStatus(false)
                    .username("marv")
                    .sudoku81(sudoku81)
                    .difficultyLevel(DifficultyLevel.EASY)
                    .build();
            sudokuRepository.save(sudokuEntity);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
