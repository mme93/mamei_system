package com.dashboard.database.service;

import com.dashboard.activity.gui.components.model.ComponentEntity;
import com.dashboard.activity.gui.components.repository.ComponentRepository;
import com.dashboard.activity.gui.components.service.EComponentIdentifier;
import com.dashboard.database.constant.ComponentName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefaultDatabaseComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public DefaultDatabaseComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public List<ComponentEntity> createDefaultDataSet() {
        List<ComponentEntity> componentEntityList= new ArrayList<>();
        if(!componentRepository.existsByName(ComponentName.CHECK_LIST)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.CHECK_LIST,
                    "BOOLEAN", EComponentIdentifier.LIST);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.CHECK_LIST_WITH_COUNT)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.CHECK_LIST_WITH_COUNT,
                    "BOOLEAN", EComponentIdentifier.LIST);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.INPUT_FIELD_STRING)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.INPUT_FIELD_STRING,
                    "STRING", EComponentIdentifier.TEXT_FIELD);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.INPUT_FIELD_NUMBER)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.INPUT_FIELD_NUMBER,
                    "NUMBER", EComponentIdentifier.TEXT_FIELD);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.INPUT_FIELD_ANY)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.INPUT_FIELD_ANY,
                    "ANY", EComponentIdentifier.TEXT_FIELD);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.INPUT_BOX_STRING)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.INPUT_BOX_STRING,
                    "STRING", EComponentIdentifier.TEXT_BLOCK_FIELD);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.LABEL)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.LABEL,
                    "STRING", EComponentIdentifier.LABEL);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.TITLE)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.TITLE,
                    "STRING", EComponentIdentifier.LABEL);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.CHECK_BOX)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.CHECK_BOX,
                    "BOOLEAN", EComponentIdentifier.CHECK_BOX);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.CHOICE_BOX)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.CHOICE_BOX,
                    "STRING", EComponentIdentifier.CHOICE_BOX);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.SEPARATOR)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.SEPARATOR,
                    "NO_VALUE", EComponentIdentifier.SEPARATOR);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.IMAGE)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.IMAGE,
                    "IMAGE", EComponentIdentifier.IMAGE);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }

        if(!componentRepository.existsByName(ComponentName.FILES)){
            ComponentEntity componentEntity = new ComponentEntity(
                    ComponentName.FILES,
                    "FILE", EComponentIdentifier.DATA_STORE);
            componentRepository.save(componentEntity);
            componentEntityList.add(componentEntity);
        }



        return componentEntityList;
    }

    public void deleteAllData() {
        componentRepository.deleteAll();
    }

    public void deleteDefaultDataset() {
        for(String name:ComponentName.componentNameList){
            if(!componentRepository.existsByName(name)){
                componentRepository.deleteByName(name);
            }
        }
    }
}
