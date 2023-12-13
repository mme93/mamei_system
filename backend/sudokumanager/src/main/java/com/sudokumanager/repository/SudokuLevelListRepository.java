package com.sudokumanager.repository;

import com.sudokumanager.model.entities.SudokuLevelListItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SudokuLevelListRepository extends JpaRepository<SudokuLevelListItemEntity, Integer> {
    List<SudokuLevelListItemEntity>findByUsername(String username);

}
