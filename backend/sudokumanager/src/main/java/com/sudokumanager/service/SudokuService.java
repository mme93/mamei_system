package com.sudokumanager.service;

import com.sudokumanager.model.dto.SudokuBlock;
import com.sudokumanager.model.dto.SudokuGrid;
import com.sudokumanager.model.entities.DifficultyLevel;
import com.sudokumanager.model.entities.SudokuEntity;
import com.sudokumanager.repository.SudokuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SudokuService {

    private final SudokuRepository sudokuRepository;

    @Autowired
    public SudokuService(SudokuRepository sudokuRepository) {
        this.sudokuRepository = sudokuRepository;
    }

    public List<SudokuBlock> generateSudokuBlocks(String username, DifficultyLevel difficultyLevel, int level) {

        return null;
    }


    public SudokuGrid generateSudokuGrid(List<SudokuBlock>sudokuBlocks) {

        return null;
    }




}
