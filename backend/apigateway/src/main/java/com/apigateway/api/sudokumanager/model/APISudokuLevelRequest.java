package com.apigateway.api.sudokumanager.model;

import com.apigateway.api.sudokumanager.controller.APIDifficultyLevel;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APISudokuLevelRequest {

    private String username;
    private APIDifficultyLevel difficultyLevel;
    private int level;

}
