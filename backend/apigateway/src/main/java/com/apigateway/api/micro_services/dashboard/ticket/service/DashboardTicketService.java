package com.apigateway.api.micro_services.dashboard.ticket.service;

import com.apigateway.api.eureka.assets.table.DashboardRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardTicketService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    public Object createTicket(Object ticket) {
        String uri = clientService.getDashboardClientAdress() + DashboardRouterTable.URI_DASH_BOARD_CREATE_TICKET;
        return webClient
                .build()
                .post()
                .uri(uri)
                .bodyValue(ticket)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Object getTicketById(Long id) {
        String uri = String.format("%s%s%s",
                clientService.getDashboardClientAdress(),
                DashboardRouterTable.URI_DASH_BOARD_GET_TICKET_BY_ID,
                id);
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public List<Object> getAllTickets() {
        String uri = clientService.getDashboardClientAdress() + DashboardRouterTable.URI_DASH_BOARD_GET_ALL_TICKETS;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }

    public void deleteTicketById(Long id) {
        String uri = String.format("%s%s%s",
                clientService.getDashboardClientAdress(),
                DashboardRouterTable.URI_DASH_BOARD_DELETE_TICKET_BY_ID,
                id);
        webClient
                .build()
                .delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public Object updateTicketStatus(String status, Long id) {
        String uri = String.format("%s%s%s",
                clientService.getDashboardClientAdress(),
                DashboardRouterTable.URI_DASH_BOARD_UPDATE_TICKET_STATUS,
                id);
        return webClient
                .build()
                .put()
                .uri(uri)
                .bodyValue(status)
                .retrieve()
                .bodyToMono(String.class).block();

    }

    public Object updateTicket(Object ticket) {
        String uri = clientService.getDashboardClientAdress() + DashboardRouterTable.URI_DASH_BOARD_UPDATE_TICKET;
        return webClient
                .build()
                .put()
                .uri(uri)
                .bodyValue(ticket)
                .retrieve()
                .bodyToMono(String.class).block();
    }
}
