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
public class PixelQuestGridElementEntity {

    public PixelQuestGridElementEntity(int rowIndex, int columnIndex, EPixelQuestBaseTexture baseTexture, EPixelQuestElementItem item) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.baseTexture = baseTexture;
        this.item = item;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private int rowIndex;

    private int columnIndex;

    @Enumerated(EnumType.STRING)
    private EPixelQuestBaseTexture baseTexture;

    @Enumerated(EnumType.STRING)
    private EPixelQuestElementItem item;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private PixelQuestMapEntity map;
}
