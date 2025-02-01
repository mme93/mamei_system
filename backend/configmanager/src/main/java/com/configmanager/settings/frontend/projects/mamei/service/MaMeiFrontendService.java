package com.configmanager.settings.frontend.projects.mamei.service;

import com.configmanager.settings.frontend.projects.mamei.model.EMameiSpecific;
import com.configmanager.settings.frontend.projects.mamei.tickettable.MaMeiTicketTableService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
public class MaMeiFrontendService {

    private final MaMeiTicketTableService maMeiTicketTableService;

    public MaMeiFrontendService(MaMeiTicketTableService maMeiTicketTableService) {
        this.maMeiTicketTableService = maMeiTicketTableService;
    }

    public List<Object> loadFrontendConfigs(String username) {

        return asList();
    }

    public Optional<Object> loadSpecificFrontendConfig(String username, EMameiSpecific specification) {
        Object config = null;
        if (specification.name().equals(EMameiSpecific.TICKET_TABLE.name())) {
            config = maMeiTicketTableService.loadConfig(username);
        }
        return Optional.of(config);
    }


    public Object createSpecificFrontendConfig(String username, EMameiSpecific eMameiSpecific, Object config) {
        switch (eMameiSpecific){
            case TICKET_TABLE -> {
                return maMeiTicketTableService.createConfig(config);
            }
        }

        return null;
    }
}
