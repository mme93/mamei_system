package com.dashboard.activity.items.repository;

import com.dashboard.activity.items.model.specification.TicketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketItemRepository extends JpaRepository<TicketItem, Long> {
    boolean existsByName(String name);
    void deleteByName(String name);
}
