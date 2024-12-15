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
@Table(name = "PixelQuestGridElement")
public class PixelQuestGridElement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private int rowIndex;
    private int columnIndex;
    private String baseTexture;
    private String items;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private PixelQuestMapEntity map;
}
