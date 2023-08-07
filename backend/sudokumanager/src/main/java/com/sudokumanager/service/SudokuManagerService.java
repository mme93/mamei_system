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
            String sudoku81 =
                    "3, 4, 8, 7, 6, 2, 5, 1, 9, 9, 6, 5, 4, 1, 3, 2, 8, 7, 2, 1, 7, 5, 9, 8, 3, 6, 4, " +
                    "5, 9, 3, 8, 4, 6, 7, 2, 1, 7, 2, 6, 3, 5, 1, 4, 9, 8, 4, 8, 1, 2, 7, 9, 6, 3, 5, " +
                    "8, 3, 4, 9, 2, 7, 1, 5, 6, 6, 7, 2, 1, 8, 5, 9, 4, 3, 1, 5, 9, 6, 3, 4, 8, 7, 2";
            var sudokuEntity = SudokuEntity
                    .builder()
                    .level(1)
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

    public void generateSudokuLevelList(String username){
        System.err.println(username);
    }

}
