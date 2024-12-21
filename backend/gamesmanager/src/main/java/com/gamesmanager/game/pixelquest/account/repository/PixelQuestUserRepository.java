package com.gamesmanager.game.pixelquest.account.repository;

import com.gamesmanager.game.pixelquest.account.entity.PixelQuestUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PixelQuestUserRepository extends JpaRepository<PixelQuestUserEntity, Long> {
}
