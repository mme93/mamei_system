package com.mameie_fsm.database.service;

import com.mameie_fsm.database.constant.SchemeName;
import com.mameie_fsm.database.constant.StandardComponentName;
import com.mameie_fsm.database.constant.component.ComponentInputField;
import com.mameie_fsm.database.constant.component.ComponentLabel;
import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import com.mameie_fsm.gui.scheme.model.Scheme;
import com.mameie_fsm.gui.scheme.model.SchemeElement;
import com.mameie_fsm.gui.scheme.model.SchemeGroup;
import com.mameie_fsm.gui.scheme.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mameie_fsm.database.constant.SchemeName.SCHEME_LIST;
import static java.util.Arrays.asList;

@Service
public class DefaultDatabaseSchemeService {


    private final SchemeRepository schemeRepository;

    @Autowired
    public DefaultDatabaseSchemeService(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    public void deleteDefaultDataset() {
        for(String schemeName:SCHEME_LIST){
            if(schemeRepository.existsBySchemeName(schemeName)){
                schemeRepository.deleteBySchemeName(schemeName);
            }
        }
    }

    public void deleteAllData() {
        schemeRepository.deleteAll();
    }

    public List<Scheme> createDefaultDataSet() {
        List<Scheme>schemeList= new ArrayList<>();
        if(!schemeRepository.existsBySchemeName(SchemeName.ADDRESS_SCHEME)){
            Scheme scheme= new Scheme(SchemeName.ADDRESS_SCHEME,"Address",false,"default",asList(
                    new SchemeGroup(1,"default",asList(
                            new SchemeElement(1,1, StandardComponentName.LABEL, ComponentLabel.H4,asList(),"Personal information",null,null),
                            new SchemeElement(2,1,StandardComponentName.INPUT_FIELD, ComponentInputField.TEXT,asList("name"),"name","name","Your name."),
                            new SchemeElement(3,1,StandardComponentName.INPUT_FIELD,ComponentInputField.NUMBER,asList("age"),"age","age","Your age."),
                            new SchemeElement(4,1,StandardComponentName.INPUT_FIELD,ComponentInputField.TEXT,asList("address"),"address",null,null)
                    )),
                    new SchemeGroup(2,"default",asList(
                            new SchemeElement(1,1, StandardComponentName.LABEL,null,asList(),null,null,null),
                            new SchemeElement(2,1,StandardComponentName.RADIO_BUTTON,null,asList(),null,null,null),
                            new SchemeElement(2,1,StandardComponentName.INPUT_FIELD,null,asList(),null,null,null)
                    ))
            ));
            schemeRepository.save(scheme);
        }
        return schemeList;
    }
}
