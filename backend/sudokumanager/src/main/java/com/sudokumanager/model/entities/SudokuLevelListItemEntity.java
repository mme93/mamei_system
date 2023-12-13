package com.sudokumanager.model.entities;

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
@Table(name = "sudoku_level_list_item")
public class SudokuLevelListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private int level;

    private String easySudoku;

    private String normalSudoku;

    private String hardSudoku;

    private String solvedEasySudoku;

    private String solvedNormalSudoku;

    private String solvedHardSudoku;

    private boolean isEasySolved;

    private boolean isNormalSolved;

    private boolean isHardSolved;

}
