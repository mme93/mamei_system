package com.apigateway.api.eureka.assets.table;

/**
 * Router table for Sudoku service.
 */
public class SudokuRouteTable {
    /** URI for generating a sudoku puzzle. */
    public static final String uri_generate_sudoku = "/sudokumanager/create";

    /** URI for loading a sudoku puzzle. */
    public static final String uri_load_sudoku = "/sudoku/load";

    /** URI for loading the list of sudoku levels. */
    public static final String uri_load_sudoku_level_list = "/sudokumanager/levelList/";
}
