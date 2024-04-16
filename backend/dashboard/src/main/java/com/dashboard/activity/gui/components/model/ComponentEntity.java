package com.dashboard.activity.gui.components.model;

import com.dashboard.activity.gui.components.service.EComponentIdentifier;
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
@Table(name = "component")
public class ComponentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    private String valueTyp;

    @Enumerated(EnumType.STRING)
    private EComponentIdentifier componentIdentifier;



    public ComponentEntity(String name, String valueTyp, EComponentIdentifier componentIdentifier) {
        this.name = name;
        this.valueTyp = valueTyp;
        this.componentIdentifier = componentIdentifier;
    }
}
