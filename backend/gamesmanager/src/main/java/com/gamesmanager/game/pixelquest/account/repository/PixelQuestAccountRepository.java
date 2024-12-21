package com.gamesmanager.game.pixelquest.account.repository;

import com.gamesmanager.game.pixelquest.account.entity.PixelQuestAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixelQuestAccountRepository extends JpaRepository<PixelQuestAccountEntity, Long> {

    Optional<PixelQuestAccountEntity> findById(Long id);

}
