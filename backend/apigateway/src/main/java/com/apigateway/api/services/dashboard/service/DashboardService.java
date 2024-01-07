package com.apigateway.api.services.dashboard.service;

import com.apigateway.api.discoveryclient.assets.table.DashboardRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    public Object createTask(Object task) {
        String uri = clientService.getDashboardClientAdress()+ DashboardRouterTable.URI_DASH_BOARD_CREATE_TASK;
        return webClient
                .build()
                .post()
                .uri(uri)
                .bodyValue(task)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Object getTaskById(Long id){
        String uri = clientService.getDashboardClientAdress()+ DashboardRouterTable.URI_DASH_BOARD_GET_TASK_BY_ID+id;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    public List<Object> getAllTask(){

        String uri = clientService.getDashboardClientAdress()+ DashboardRouterTable.URI_DASH_BOARD_GET_ALL_TASK;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }
}
