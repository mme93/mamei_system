package com.gamesmanager.game.sudoku.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sudoku")
public class SudokuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int level;

    private boolean processingStatus;

    private String username;

    private String sudoku81;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

}
