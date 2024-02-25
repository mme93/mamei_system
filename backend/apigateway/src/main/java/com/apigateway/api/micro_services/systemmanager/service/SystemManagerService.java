package com.apigateway.api.micro_services.systemmanager.service;

import com.apigateway.api.eureka.assets.table.SystemManagerRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Service class responsible for interacting with the system manager microservice.
 */
@Service
@RequiredArgsConstructor
public class SystemManagerService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    /**
     * Retrieves a ping response from the system manager service.
     * @return A ping response.
     */
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

    /**
     * Retrieves the status of a specific microservice from the system manager service.
     * @param microServiceName The name of the microservice.
     * @return The status of the microservice.
     */
    public Object getMicroServiceStatus(String microServiceName) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_microservices_status + "/" + microServiceName;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    /**
     * Retrieves the statuses of all microservices from the system manager service.
     * @return A list of microservice statuses.
     */
    public List<Object> getMicroServicesStatus() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_microservices_status;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    /**
     * Starts a new process job using the provided process object.
     * @param process The process object to start.
     * @return True if the process job was started successfully, false otherwise.
     */
    public Boolean startProcess(Object process) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_process_new_job;
        return webClient
                .build()
                .post()
                .uri(uri)
                .bodyValue(process)
                .retrieve()
                .bodyToMono(Boolean.class).block();
    }

    /**
     * Retrieves a list of process elements for the user interface.
     * @return A list of process elements.
     */
    public List<Object> getProcessElementUI() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_process_ui;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    /**
     * Creates a sorted and executed process user interface based on the provided process list.
     * @param processList The list of process objects.
     * @return An object representing the sorted and executed process user interface.
     */
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

    /**
     * Sets the default process configuration.
     */
    public void setDefault() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_process_set_default;
        webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    /**
     * Creates a task protocol for the given task signature and current username.
     * @param task_signature The signature of the task.
     * @param currentUsername The current username.
     */
    public void createTaskProtocol(String task_signature, String currentUsername) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_create_task+"/"+task_signature+"/"+currentUsername;
        webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    /**
     * Closes a task protocol with the given task signature.
     * @param task_signature The signature of the task.
     */
    public void closeTaskProtocol(String task_signature) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_post_close_task +"/"+task_signature;
        webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Void.class).block();
    }

    /**
     * Retrieves a list of task process protocols.
     * @return A list of task process protocols.
     */
    public List<Object> getTaskProcessProtocols() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_task_protocol;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    /**
     * Retrieves the task process protocol for the given task signature.
     * @param task_signature The signature of the task.
     * @return The task process protocol.
     */
    public Object getTaskProcessProtocol(String task_signature) {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_get_load_task_protocol+"/"+task_signature;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    /**
     * Updates the user comment for a task.
     * @param userComment The user comment object.
     */
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
