package com.apigateway.api.micro_services.dashboard.ticket.service;

import com.apigateway.api.eureka.assets.table.DashboardRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardTicketFilterService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    public List<Object> getAllTicketTableFilter() {
        String uri = clientService.getDashboardClientAdress() + DashboardRouterTable.URI_DASH_BOARD_GET_ALL_TICKET_TABLE_FILTER;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    public Object getTicketTableFilterById(Long id) {
        String uri = String.format("%s%s%s",
                clientService.getDashboardClientAdress(),
                DashboardRouterTable.URI_DASH_BOARD_GET_TICKET_TABLE_FILTER_BY_ID,
                id);
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    public Object createTicketTableFilter(Object filterCreateDto) {
        String uri = clientService.getDashboardClientAdress() + DashboardRouterTable.URI_DASH_BOARD_CREATE_TICKET_TABLE_FILTER;
        return webClient
                .build()
                .post()
                .uri(uri)
                .bodyValue(filterCreateDto)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Object updateTicketTableFilter(Object tableFilterResponseDto) {
        String uri = clientService.getDashboardClientAdress() + DashboardRouterTable.URI_DASH_BOARD_UPDATE_TICKET_TABLE_FILTER;
        return webClient
                .build()
                .put()
                .uri(uri)
                .bodyValue(tableFilterResponseDto)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public void deleteTicketTableFilterById(Long id) {
        String uri = String.format("%s%s%s",
                clientService.getDashboardClientAdress(),
                id,
                DashboardRouterTable.URI_DASH_BOARD_DELETE_TICKET_TABLE_FILTER_BY_ID);
        webClient
                .build()
                .delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
    }
}
