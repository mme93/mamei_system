package com.dashboard.activity.gui.scheme.repository;

import com.dashboard.activity.gui.scheme.model.SchemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRepository extends JpaRepository<SchemeEntity, Long> {

    boolean existsByName(String name);
    void deleteByName(String name);
}
