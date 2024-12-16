package com.gamesmanager.game.pixelquest.test;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PixelQuestAccount")
public class PixelQuestAccountEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String userName;

    private Long currentWorldId;

    private Long currentMapId;

    private int mapColIndex;

    private int mapRowIndex;


    public PixelQuestAccountEntity(String userName, Long currentWorldId, Long currentMapId, int mapColIndex, int mapRowIndex) {
        this.userName = userName;
        this.currentWorldId = currentWorldId;
        this.currentMapId = currentMapId;
        this.mapColIndex = mapColIndex;
        this.mapRowIndex = mapRowIndex;
    }
}
