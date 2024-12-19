package com.gamesmanager.game.pixelquest.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixelQuestAccountRepository extends JpaRepository<PixelQuestAccountEntity, Long> {

    Optional<PixelQuestAccountEntity> findById(Long id);

}
