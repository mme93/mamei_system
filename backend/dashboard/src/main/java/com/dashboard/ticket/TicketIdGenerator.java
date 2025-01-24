package com.dashboard.ticket;

import org.springframework.stereotype.Component;

@Component
public class TicketIdGenerator {

    public static final String DASHBOARD ="DASHBOARD";
    public static final String API_GATEWAY ="API_GATEWAY";


    public String generateId(Long id, String projectLabel){
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

}
