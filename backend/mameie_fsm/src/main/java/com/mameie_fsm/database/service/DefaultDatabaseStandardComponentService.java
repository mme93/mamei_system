package com.mameie_fsm.database.service;

import com.mameie_fsm.database.constant.StandardComponentName;
import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import com.mameie_fsm.gui.components.standard.repository.StandardComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mameie_fsm.database.constant.StandardComponentName.standardComponentNameList;
import static java.util.Arrays.asList;

@Service
public class DefaultDatabaseStandardComponentService {

    private final StandardComponentRepository standardComponentRepository;

    @Autowired
    public DefaultDatabaseStandardComponentService(StandardComponentRepository standardComponentRepository) {
        this.standardComponentRepository = standardComponentRepository;
    }

    public void deleteAllData() {
        standardComponentRepository.deleteAll();
    }

    public void deleteDefaultDataset() {
        List<StandardComponent>standardComponentList=standardComponentRepository.findAll();
        for(String name:standardComponentNameList){
            if(standardComponentRepository.existsByValue(name)){
                standardComponentRepository.delete(
                        standardComponentList.stream().filter(
                                standardComponent -> standardComponent.getValue().equals(name)).findFirst().get()
                );
            }
        }
    }

    public List<StandardComponent> createDefaultDataSet() {
        List<StandardComponent>standardComponentList = new ArrayList<>();

        if(!standardComponentRepository.existsByValue(StandardComponentName.LABEL)){
            standardComponentRepository.save(new StandardComponent(
                    "Label",
                    StandardComponentName.LABEL,
                    asList("TITLE","H1","H2","H3","H4","P"),
                    asList("default"),
                    "From title over subtitles to normal paragraphs."
            ));
        }
        if(!standardComponentRepository.existsByValue(StandardComponentName.LIST)){
            standardComponentRepository.save(new StandardComponent(
                    "List",
                    StandardComponentName.LIST,
                    asList("WITH_CHECKBOX","ONLY_STRING"),
                    asList("default"),
                    "Different types of List."
            ));
        }
        if(!standardComponentRepository.existsByValue(StandardComponentName.CHECKBOX)){
            standardComponentRepository.save(new StandardComponent(
                    "Checkbox",
                    StandardComponentName.CHECKBOX,
                    asList("SINGLE_CHECKBOX","MULTIPLY_CHECKBOX"),
                    asList("default"),
                    "Single or multiply Checkboxes."
            ));
        }
        if(!standardComponentRepository.existsByValue(StandardComponentName.INPUT_FIELD)){
            standardComponentRepository.save(new StandardComponent(
                    "Input field",
                    StandardComponentName.INPUT_FIELD,
                    asList("TEXT","AREA","NUMBER","DATE"),
                    asList("default"),
                    "Different types of input fields like number or string input."
            ));
        }
        if(!standardComponentRepository.existsByValue(StandardComponentName.RADIO_BUTTON)){
            standardComponentRepository.save(new StandardComponent(
                    "Radio Button",
                    StandardComponentName.RADIO_BUTTON,
                    asList("STANDARD"),
                    asList("default"),
                    "Radio Buttons."
            ));
        }
        return standardComponentList;
    }
}
