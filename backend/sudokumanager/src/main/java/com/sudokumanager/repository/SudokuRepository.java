package com.sudokumanager.repository;

import com.sudokumanager.model.entities.SudokuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SudokuRepository extends JpaRepository<SudokuEntity, Integer> {

    List<SudokuEntity> findByUsername(String username);

}
