package com.apigateway.api.micro_services.systemmanager.service;

import com.apigateway.api.eureka.assets.table.SystemManagerRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemManagerService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    @Async
    public String getPing() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_ping;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Object getMicroServiceStatus(String microServiceName) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_microservices_status + "/" + microServiceName;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    public List<Object> getMicroServicesStatus() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_microservices_status;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }


    public Boolean startProcess(Object process) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_microservices_status;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class).block();
    }

    public List<Object> getProcessElementUI() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_process_ui;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    public Object createSortedExecuteProcessUI(List<Object> processList) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_sort_process;
        return webClient
                .build()
                .put()
                .uri(uri)
                .bodyValue(processList)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    public void setDefault() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_process_set_default;
        webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    public void createTaskProtocol(String task_signature, String currentUsername) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_create_task+"/"+task_signature+"/"+currentUsername;
        webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    public void closeTaskProtocol(String task_signature) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_post_close_task +"/"+task_signature;
        webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    public List<Object> getTaskProcessProtocols() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_task_protocol;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    public Object getTaskProcessProtocol(String task_signature) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_load_task_protocol+"/"+task_signature;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    public void updateUserComment(Object userComment) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_post_comment;
        webClient
                .build()
                .post()
                .uri(uri)
                .bodyValue(userComment)
                .retrieve()
                .bodyToMono(Void.class).block();
    }


}
