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
public class SchemeElement {

    private int position;

    private int groupNumber;

    private String standardComponentName;

    private String specification;

    private List<String>subContent;

    private String defaultContent;

    private String placeHolder;

    private String hint;

}
