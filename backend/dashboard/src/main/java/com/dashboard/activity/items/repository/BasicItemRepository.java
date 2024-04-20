package com.dashboard.activity.items.repository;

import com.dashboard.activity.items.model.basic.BasicItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicItemRepository extends JpaRepository<BasicItem, Long> {
    boolean existsByName(String name);
    void deleteByName(String name);
}
