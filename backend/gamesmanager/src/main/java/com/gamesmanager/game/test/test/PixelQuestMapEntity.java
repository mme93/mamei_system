package com.gamesmanager.game.test.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamesmanager.game.test.level.model.EPixelQuestMap;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PixelQuestMap")
public class PixelQuestMapEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    private int height;
    private int width;

    private EPixelQuestMap pixelQuestMap;

    @ManyToOne
    @JoinColumn(name = "world_id", nullable = false)
    @JsonIgnore
    private PixelQuestWorldEntity world;

    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PixelQuestGridElementEntity> gridElements;

}
