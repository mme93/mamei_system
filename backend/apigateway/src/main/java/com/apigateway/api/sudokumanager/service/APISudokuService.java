package com.apigateway.api.sudokumanager.service;

import com.apigateway.api.discoveryclient.assets.SudokuRouteTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.api.sudokumanager.model.APISudokuLevelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class APISudokuService {

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;

    @Async
    public Boolean loadSudoku(APISudokuLevelRequest levelRequest) {

        String uri = discoveryClientService.getSudokuClientAdress() + SudokuRouteTable.uri_load_sudoku;
        System.err.println(uri);
        webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(levelRequest))
                .retrieve()
                .bodyToMono(Void.class).block();
        return true;
    }

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

}
