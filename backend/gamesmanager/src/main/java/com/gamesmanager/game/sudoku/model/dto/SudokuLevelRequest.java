package com.gamesmanager.game.sudoku.model.dto;

import com.gamesmanager.game.sudoku.model.entities.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SudokuLevelRequest {

    private String username;
    private DifficultyLevel difficultyLevel;
    private int level;

}
