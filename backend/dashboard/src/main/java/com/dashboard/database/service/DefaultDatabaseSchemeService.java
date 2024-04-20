package com.dashboard.database.service;

import com.dashboard.activity.gui.scheme.model.SchemeEntity;
import com.dashboard.activity.gui.scheme.repository.SchemeRepository;
import com.dashboard.database.constant.ComponentName;
import com.dashboard.database.constant.SchemeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.dashboard.database.constant.ComponentName.*;

@Service
public class DefaultDatabaseSchemeService {

    private final SchemeRepository schemeRepository;

    @Autowired
    public DefaultDatabaseSchemeService(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    public List<SchemeEntity> createDefaultDataSet() {
        List<SchemeEntity> schemeEntityList = new ArrayList<>();
        if (!schemeRepository.existsByName(SchemeName.CHECK_LIST)) {
            SchemeEntity schemeEntity = new SchemeEntity(
                    SchemeName.CHECK_LIST,String.format("%s, %s",TITLE,CHECK_LIST),true);
            schemeRepository.save(schemeEntity);
            schemeEntityList.add(schemeEntity);
        }

        if (!schemeRepository.existsByName(SchemeName.CHECK_LIST_WITH_COUNT)) {
            SchemeEntity schemeEntity = new SchemeEntity(
                    SchemeName.CHECK_LIST_WITH_COUNT,String.format("%s, %s",TITLE,CHECK_LIST_WITH_COUNT),true);
            schemeRepository.save(schemeEntity);
            schemeEntityList.add(schemeEntity);
        }

        if (!schemeRepository.existsByName(SchemeName.FORM)) {
            SchemeEntity schemeEntity = new SchemeEntity(
                    SchemeName.FORM,String.format("%s, %s",TITLE,INPUT_FIELD_ANY),true);
            schemeRepository.save(schemeEntity);
            schemeEntityList.add(schemeEntity);
        }

        if (!schemeRepository.existsByName(SchemeName.NOTE)) {
            SchemeEntity schemeEntity = new SchemeEntity(
                    SchemeName.NOTE,String.format("%s, %s",TITLE,INPUT_BOX_STRING),true);
            schemeRepository.save(schemeEntity);
            schemeEntityList.add(schemeEntity);
        }
        return schemeEntityList;
    }

    public void deleteAllData() {
        schemeRepository.deleteAll();
    }

    public void deleteDefaultDataset() {
        for (String name : ComponentName.componentNameList) {
            if (!schemeRepository.existsByName(name)) {
                schemeRepository.deleteByName(name);
            }
        }
    }

}
