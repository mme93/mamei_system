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
    private String schemeName;

    @Column(unique = true)
    private String schemeViewName;

    private boolean isPrivate;

    private String styleClass;

    @Lob
    private List<SchemeGroup> schemeGroupList;

    public Scheme(String schemeName, String schemeViewName, boolean isPrivate, String styleClass, List<SchemeGroup> schemeGroupList) {
        this.schemeName = schemeName;
        this.schemeViewName = schemeViewName;
        this.isPrivate = isPrivate;
        this.styleClass = styleClass;
        this.schemeGroupList = schemeGroupList;
    }
}
