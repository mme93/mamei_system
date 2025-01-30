package com.dashboard.ticket.model.mapper;

import com.dashboard.ticket.model.dto.TicketTableFilterCreateDto;
import com.dashboard.ticket.model.dto.TicketTableFilterResponseDto;
import com.dashboard.ticket.model.entity.TicketTableFilterEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TicketFilterMapper {

    public static TicketTableFilterResponseDto entityMapToDto(TicketTableFilterEntity entity) throws JsonProcessingException {
        return new TicketTableFilterResponseDto(
                entity.getFilterName(),
                entity.isCREATED(),
                entity.isWAITING(),
                entity.isREFINEMENT(),
                entity.isIN_PROGRESS(),
                entity.isDONE(),
                stringToList(entity.getDisplayedColumns())
        );
    }

    public static TicketTableFilterEntity dtoMapToEntity(TicketTableFilterCreateDto dto) throws JsonProcessingException {
        return new TicketTableFilterEntity(
                dto.getFilterName(),
                dto.isCREATED(),
                dto.isWAITING(),
                dto.isREFINEMENT(),
                dto.isIN_PROGRESS(),
                dto.isDONE(),
                listToString(dto.getDisplayedColumns()));

    }

    public static TicketTableFilterEntity dtoMapToEntity(TicketTableFilterResponseDto dto) throws JsonProcessingException {
        return new TicketTableFilterEntity(
                dto.getFilterName(),
                dto.isCREATED(),
                dto.isWAITING(),
                dto.isREFINEMENT(),
                dto.isIN_PROGRESS(),
                dto.isDONE(),
                listToString(dto.getDisplayedColumns()));

    }

    private static List<String> stringToList(String value) throws JsonProcessingException {
        return new ObjectMapper().readValue(value, ArrayList.class);
    }

    private static String listToString(List<String> list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
}
