package com.sudokumanager.repository;


import com.sudokumanager.model.entities.SudokuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SudokuRepository extends JpaRepository<SudokuEntity, Integer> {
    Optional<SudokuEntity> findByUsername(String username);
}
