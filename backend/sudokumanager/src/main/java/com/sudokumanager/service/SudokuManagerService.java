package com.sudokumanager.service;

import com.sudokumanager.model.entities.DifficultyLevel;
import com.sudokumanager.model.entities.SudokuEntity;
import com.sudokumanager.model.entities.SudokuLevelListItemEntity;
import com.sudokumanager.repository.SudokuLevelListRepository;
import com.sudokumanager.repository.SudokuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SudokuManagerService {

    private final SudokuRepository sudokuRepository;
    private final SudokuLevelListRepository sudokuLevelListRepository;

    @Autowired
    public SudokuManagerService(SudokuRepository sudokuRepository, SudokuLevelListRepository sudokuLevelListRepository) {
        this.sudokuRepository = sudokuRepository;
        this.sudokuLevelListRepository = sudokuLevelListRepository;
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

    public List<SudokuLevelListItemEntity> generateSudokuLevelList(String username) {
        List<SudokuLevelListItemEntity> resultList = sudokuLevelListRepository.findByUsername(username);
        if (resultList.size() == 0) {
            return initUserLevelList(username);
        }
        return resultList;
    }

    public List<SudokuLevelListItemEntity> initUserLevelList(String username) {
        List<SudokuLevelListItemEntity> newList = new ArrayList<>();
        for(SudokuLevelListItemEntity entity:sudokuLevelListRepository.findByUsername("default")){
            var sudokuLevelListItemEntity = SudokuLevelListItemEntity
                    .builder()
                    .level(entity.getLevel())
                    .username(username)
                    .easySudoku(entity.getEasySudoku())
                    .normalSudoku(entity.getNormalSudoku())
                    .hardSudoku(entity.getHardSudoku())
                    .solvedEasySudoku(entity.getSolvedEasySudoku())
                    .solvedNormalSudoku(entity.getSolvedNormalSudoku())
                    .solvedHardSudoku(entity.getSolvedHardSudoku())
                    .isEasySolved(false)
                    .isNormalSolved(false)
                    .isHardSolved(false)
                    .build();
            newList.add(sudokuLevelListItemEntity);
        }
        sudokuLevelListRepository.saveAll(newList);
        return newList;
    }

    public void initSudokuLevelList() {
        List<SudokuLevelListItemEntity> sudokuLevelListItemEntityList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            var sudokuLevelListItemEntity = SudokuLevelListItemEntity
                    .builder()
                    .level(i + 1)
                    .username("default")
                    .easySudoku("")
                    .normalSudoku("")
                    .hardSudoku("")
                    .solvedEasySudoku("")
                    .solvedNormalSudoku("")
                    .solvedHardSudoku("")
                    .isEasySolved(false)
                    .isNormalSolved(false)
                    .isHardSolved(false)
                    .build();
            sudokuLevelListItemEntityList.add(sudokuLevelListItemEntity);
        }
        sudokuLevelListRepository.saveAll(sudokuLevelListItemEntityList);
    }
}
