package com.mameie_fsm.gui.components.standard.repository;

import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandardComponentRepository extends JpaRepository<StandardComponent, Long> {
    boolean existsByValue(String value);
    Optional<StandardComponent>findByValue(String value);
}
