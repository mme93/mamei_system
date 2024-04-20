package com.dashboard.activity.items.repository;

import com.dashboard.activity.items.model.ItemNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemNamesRepository extends JpaRepository<ItemNames, Long> {
}
