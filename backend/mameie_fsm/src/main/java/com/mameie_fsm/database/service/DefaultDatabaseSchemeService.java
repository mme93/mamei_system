package com.mameie_fsm.database.service;

import com.mameie_fsm.database.constant.StandardComponentName;
import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import com.mameie_fsm.gui.components.standard.repository.StandardComponentRepository;
import com.mameie_fsm.gui.scheme.model.Scheme;
import com.mameie_fsm.gui.scheme.model.SchemeComponent;
import com.mameie_fsm.gui.scheme.repository.SchemeComponentRepository;
import com.mameie_fsm.gui.scheme.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mameie_fsm.database.constant.SchemeName.ADDRESS_SCHEME;
import static com.mameie_fsm.database.constant.SchemeName.SCHEME_LIST;
import static java.util.Arrays.asList;


@Service
public class DefaultDatabaseSchemeService {


    private final SchemeRepository schemeRepository;
    private final StandardComponentRepository standardComponentRepository;
    private final SchemeComponentRepository schemeComponentRepository;

    @Autowired
    public DefaultDatabaseSchemeService(SchemeRepository schemeRepository, StandardComponentRepository standardComponentRepository, SchemeComponentRepository schemeComponentRepository) {
        this.schemeRepository = schemeRepository;
        this.standardComponentRepository = standardComponentRepository;
        this.schemeComponentRepository = schemeComponentRepository;
    }

    public void deleteDefaultDataset() {
        for (String schemeName : SCHEME_LIST) {
            if (schemeRepository.existsByName(schemeName)) {
                schemeRepository.deleteByName(schemeName);
            }
        }
    }

    public void deleteAllData() {
        schemeRepository.deleteAll();
    }

    public List<Scheme> createDefaultDataSet() {
        List<Scheme> schemeList = new ArrayList<>();
        if (!schemeRepository.existsByName(ADDRESS_SCHEME)) {
            StandardComponent label = standardComponentRepository.findByValue(StandardComponentName.LABEL).get();
            StandardComponent inputField = standardComponentRepository.findByValue(StandardComponentName.INPUT_FIELD).get();
            SchemeComponent addressComponent=new SchemeComponent(
                    label.getId(),label.getValue(), "Address", asList("Test"), 1, label.getSpecificationList().get(0)
            );
            addressComponent=schemeComponentRepository.save(addressComponent);

            SchemeComponent streetComponent=new SchemeComponent(
                    inputField.getId(),inputField.getValue(), "Street",asList("Test"), 2, inputField.getSpecificationList().get(0)
            );
            streetComponent=schemeComponentRepository.save(streetComponent);

            SchemeComponent townComponent=new SchemeComponent(
                    inputField.getId(),inputField.getValue(), "Town", asList("Test"), 3, inputField.getSpecificationList().get(0)
            );
            townComponent=schemeComponentRepository.save(townComponent);

            SchemeComponent plzComponent=new SchemeComponent(
                    inputField.getId(),inputField.getValue(), "Plz", asList("Test"), 4, inputField.getSpecificationList().get(0)
            );
            plzComponent=schemeComponentRepository.save(plzComponent);


            schemeRepository.save(new Scheme(
                    ADDRESS_SCHEME, "default", "Save address from Person.",true,
                    asList(addressComponent.getComponentId(),streetComponent.getComponentId(),
                            townComponent.getComponentId(),plzComponent.getComponentId()
                    )
            ));
        }
        return schemeList;
    }
}
