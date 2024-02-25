package com.apigateway.api.micro_services.sudokumanager.service;

import com.apigateway.api.eureka.assets.table.SudokuRouteTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import com.apigateway.api.micro_services.sudokumanager.model.dto.APISudokuLevelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service class responsible for interacting with the Sudoku microservice.
 */
@Service
@RequiredArgsConstructor
public class APISudokuService {

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;

    /**
     * Asynchronously loads a Sudoku puzzle based on the provided level request.
     * @param levelRequest The level request containing the necessary information to load the Sudoku puzzle.
     * @return An object representing the loaded Sudoku puzzle.
     */
    @Async
    public Object loadSudoku(APISudokuLevelRequest levelRequest) {
        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_load_sudoku;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(levelRequest))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    /**
     * Asynchronously generates a new Sudoku puzzle.
     * @return A Mono<Boolean> indicating the success or failure of the operation.
     */
    @Async
    public Mono<Boolean> generateSudoku() {
        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_generate_sudoku;
        return webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    /**
     * Asynchronously loads a list of available Sudoku puzzle levels for a given user.
     * @param username The username for which to load the Sudoku puzzle level list.
     * @return An object representing the list of available Sudoku puzzle levels.
     */
    @Async
    public Object loadSudokuLevelList(String username) {
        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_load_sudoku_level_list+username;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(username))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

}
