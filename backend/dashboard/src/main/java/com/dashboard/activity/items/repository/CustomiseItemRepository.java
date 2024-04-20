package com.dashboard.activity.items.repository;


import com.dashboard.activity.items.model.specification.CustomiseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomiseItemRepository extends JpaRepository<CustomiseItem, Long> {
    boolean existsByName(String name);
    void deleteByName(String name);
}
