package com.mameie_fsm.gui.scheme.model;

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
@Table(name = "scheme")
public class Scheme {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String styleClass;

    private String description;

    private boolean isOpenSource;

    @Lob
    private List<Long> componentIds;

    public Scheme(String name, String styleClass, String description, boolean isOpenSource, List<Long> componentIds) {
        this.name = name;
        this.styleClass = styleClass;
        this.description = description;
        this.isOpenSource = isOpenSource;
        this.componentIds = componentIds;
    }
}
