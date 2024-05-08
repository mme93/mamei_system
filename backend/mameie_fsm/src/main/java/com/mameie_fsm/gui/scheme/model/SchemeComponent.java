package com.mameie_fsm.gui.scheme.model;

import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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
@Table(name = "scheme_component")
public class SchemeComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long componentId;

    private String componentName;

    private String defaultValue;

    @Lob
    private List<String> valueList;

    private int position;

    private String specification;

    public SchemeComponent(Long componentId, String componentName, String defaultValue, List<String> valueList, int position, String specification) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.defaultValue = defaultValue;
        this.valueList = valueList;
        this.position = position;
        this.specification = specification;
    }
}
