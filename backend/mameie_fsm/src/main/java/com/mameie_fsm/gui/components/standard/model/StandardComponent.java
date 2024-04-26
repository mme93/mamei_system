package com.mameie_fsm.gui.components.standard.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "standard_component")
public class StandardComponent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String viewValue;

    @Column(unique = true, nullable = false)
    private String value;

    @Lob
    private List<String>specificationList;

    @Lob
    private List<String>styleClassList;

    private String description;


    public StandardComponent(String viewValue, String value, List<String> specificationList, List<String> styleClassList, String description) {
        this.viewValue = viewValue;
        this.value = value;
        this.specificationList = specificationList;
        this.styleClassList = styleClassList;
        this.description = description;
    }
}
