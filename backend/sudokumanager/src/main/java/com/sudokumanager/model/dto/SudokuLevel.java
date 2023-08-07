package com.sudokumanager.model.dto;

import com.sudokumanager.model.entities.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SudokuLevel {

    private String username;
    private DifficultyLevel difficultyLevel;
    private int level;
    private SudokuGrid sudokuGrid;

}
