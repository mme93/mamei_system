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

    private String name;

    private String valueTyp;

    private EComponentIdentifier componentIdentifier;

    private String defaultValue;

}
