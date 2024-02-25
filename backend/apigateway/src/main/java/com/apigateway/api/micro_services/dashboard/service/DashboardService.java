package com.apigateway.api.micro_services.dashboard.service;

import com.apigateway.api.eureka.assets.table.DashboardRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import com.apigateway.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Service class responsible for interacting with the dashboard microservice.
 */
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;
    private final UserService userService;

    /**
     * Creates a new task via the dashboard microservice.
     * @param task The task object to be created.
     * @return The response object from the dashboard microservice.
     */
    public Object createTask(Object task) {
        String uri = clientService.getDashboardClientAdress()+ DashboardRouterTable.URI_DASH_BOARD_CREATE_TASK;
        return webClient
                .build()
                .post()
                .uri(uri)
                .header("userId", String.valueOf(userService.getCurrentUserId()))
                .header("userName", userService.getCurrentUsername())
                .bodyValue(task)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    /**
     * Retrieves a task by its ID from the dashboard microservice.
     * @param id The ID of the task to retrieve.
     * @return The task object corresponding to the given ID.
     */
    public Object getTaskById(Long id){
        String uri = clientService.getDashboardClientAdress()+ DashboardRouterTable.URI_DASH_BOARD_GET_TASK_BY_ID+id;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    /**
     * Retrieves all tasks from the dashboard microservice.
     * @return A list of task objects.
     */
    public List<Object> getAllTask(){
        String uri = clientService.getDashboardClientAdress()+ DashboardRouterTable.URI_DASH_BOARD_GET_ALL_TASK;
        return webClient
                .build()
                .get()
                .uri(uri)
                .header("userId", String.valueOf(userService.getCurrentUserId()))
                .header("userName", userService.getCurrentUsername())
                .retrieve()
                .bodyToMono(List.class).block();
    }
}
