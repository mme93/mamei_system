package com.gamesmanager.game.pixelquest.test;

import com.gamesmanager.game.pixelquest.world.model.PixelQuestWorld;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixelQuestWorldRepository extends JpaRepository<PixelQuestWorldEntity, Long> {

}