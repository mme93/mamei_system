package com.dashboard.activity.gui.components.repository;

import com.dashboard.activity.gui.components.model.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, Long> {

    boolean existsByName(String name);
    void deleteByName(String name);
}
