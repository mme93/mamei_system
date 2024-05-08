package com.mameie_fsm.gui.scheme.repository;


import com.mameie_fsm.gui.scheme.model.SchemeComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemeComponentRepository extends JpaRepository<SchemeComponent, Long> {
}
