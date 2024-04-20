package com.dashboard.activity.items.repository;

import com.dashboard.activity.items.model.EItemValueTyp;
import com.dashboard.activity.items.model.value.typ.ItemValueTyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemValueTypRepository extends JpaRepository<ItemValueTyp, Long> {

}
