package com.dashboard.activity.gui.scheme.model;

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
@Table(name = "scheme")
public class SchemeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String componentNameList;

    private boolean isOpenSource;

    public SchemeEntity(String name, String componentNameList, boolean isOpenSource) {
        this.name = name;
        this.componentNameList = componentNameList;
        this.isOpenSource = isOpenSource;
    }
}
