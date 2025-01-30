package com.dashboard.ticket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TicketTableFilterResponseDto {

    private String filterName;

    private boolean isCREATED;

    private boolean isWAITING;

    private boolean isREFINEMENT;

    private boolean isIN_PROGRESS;

    private boolean isDONE;

    private List<String> displayedColumns;
}
