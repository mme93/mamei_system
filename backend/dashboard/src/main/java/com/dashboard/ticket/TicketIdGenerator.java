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

    public String generateSuffix(String projectLabel){
        switch (projectLabel){
            case DASHBOARD:
                return "DB";
            case API_GATEWAY:
                return "API_GW";
            default:
                throw new RuntimeException(String.format("Label found by name %s.",projectLabel));
        }
    }

    public String generateSuffix(EProject project){
        switch (project) {
            case API_GATEWAY:
                return "AG";
            case CONFIG_MANAGER:
                return "CM";
            case DASHBOARD:
                return "DAB";
            case DATABASE_MANAGE:
                return "DB";
            case DATA_STORAGE_MANAGER:
                return "DS";
            case GAMES_MANAGER:
                return "G";
            case HEALTH_MANAGER:
                return "H";
            case MAMEI_FSM:
                return "MF";
            case MASS_DATA_POOL:
                return "MD";
            case SECURITY_GATEWAY:
                return "SG";
            case SERVICE_REGISTRY:
                return "SR";
            case MAMEI_SYSTEM:
                return "SG";
            case DB_MANAGER:
                return "SR";
            default:
                throw new IllegalArgumentException("Unknown MicroServiceProject: " + project);
        }
    }

}
