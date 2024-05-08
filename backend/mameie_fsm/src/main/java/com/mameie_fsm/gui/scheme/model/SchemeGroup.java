package com.mameie_fsm.gui.scheme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchemeGroup {

    private int groupNumber;

    private String styleClass;

    private List<SchemeElement>schemeElementList;

}
