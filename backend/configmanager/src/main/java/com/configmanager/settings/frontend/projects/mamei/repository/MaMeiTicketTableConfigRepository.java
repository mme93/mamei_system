package com.configmanager.settings.frontend.projects.mamei.repository;

import com.configmanager.settings.frontend.projects.mamei.model.entity.MaMeiTicketTableConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaMeiTicketTableConfigRepository extends JpaRepository<MaMeiTicketTableConfigEntity, Long> {
    void deleteByOwner(String owner);
    Optional<MaMeiTicketTableConfigEntity>findByOwner(String owner);
}
