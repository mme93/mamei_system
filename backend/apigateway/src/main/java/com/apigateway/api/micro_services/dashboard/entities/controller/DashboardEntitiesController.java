package com.apigateway.api.micro_services.dashboard.entities.controller;

import com.apigateway.api.micro_services.dashboard.entities.service.DashboardEntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard_entities")
public class DashboardEntitiesController {

    private final DashboardEntitiesService dashboardEntitiesService;

    @Autowired
    public DashboardEntitiesController(DashboardEntitiesService dashboardEntitiesService) {
        this.dashboardEntitiesService = dashboardEntitiesService;
    }



}
