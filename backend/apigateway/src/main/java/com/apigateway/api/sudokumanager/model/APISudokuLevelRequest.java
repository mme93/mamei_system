package com.apigateway.api.sudokumanager.model;

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
