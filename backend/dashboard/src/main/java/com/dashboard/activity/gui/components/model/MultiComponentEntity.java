package com.dashboard.activity.gui.components.model;

import com.dashboard.activity.gui.components.service.EMultiComponentIdentifier;
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
@Table(name = "multi_component")
public class MultiComponentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private EMultiComponentIdentifier multiComponentIdentifier;

    private String componentsJSON;
}
