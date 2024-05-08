package com.mameie_fsm.gui.scheme.repository;

import com.mameie_fsm.gui.scheme.model.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {
    boolean existsByName(String name);
    void deleteByName(String name);
}
