package com.sudokumanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SudokuGrid {

    private List<SudokuRows> sudokuRows;
    private List<SudokuColumns> sudokuColumns;
    private List<SudokuBlock> sudokuBlocks;
}
