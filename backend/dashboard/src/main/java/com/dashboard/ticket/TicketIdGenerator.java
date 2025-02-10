package com.dashboard.ticket;

import com.dashboard.ticket.model.enums.EProject;
import org.springframework.stereotype.Component;

@Component
public class TicketIdGenerator {

    public static final String DASHBOARD ="DASHBOARD";
    public static final String API_GATEWAY ="API_GATEWAY";


    public String generateId(Long id, EProject projectLabel){
        return String.format("%s-%s",generateSuffix(projectLabel),id);
    }

    public String generateSuffix(EProject project){
        switch (project) {
            case API_GATEWAY:
                return "B-AG";
            case CONFIG_MANAGER:
                return "B-CM";
            case DASHBOARD:
                return "B-DAB";
            case DATABASE_MANAGE:
                return "B-DB";
            case DATA_STORAGE_MANAGER:
                return "B-DS";
            case GAMES_MANAGER:
                return "B-G";
            case HEALTH_MANAGER:
                return "B-H";
            case MAMEI_FSM:
                return "B-MF";
            case MASS_DATA_POOL:
                return "B-MD";
            case SECURITY_GATEWAY:
                return "B-SG";
            case SERVICE_REGISTRY:
                return "B-SR";
            case MAMEI_SYSTEM:
                return "F-MS";
            case DB_MANAGER:
                return "F-SR";
            default:
                throw new IllegalArgumentException("Unknown MicroServiceProject: " + project);
        }
    }

}
