package com.configmanager.settings.frontend.projects.mamei.service;

import com.configmanager.settings.frontend.projects.mamei.model.dto.MaMeiTicketTableConfigDto;
import com.configmanager.settings.frontend.projects.mamei.model.entity.MaMeiTicketTableConfigEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaMeiConfigWrapperService {

    public MaMeiTicketTableConfigEntity wrapToTicketTableConfigEntity(Object object) throws JsonProcessingException {
        MaMeiTicketTableConfigDto dto = (MaMeiTicketTableConfigDto) object;
        return wrapToTicketTableConfigEntity(dto);
    }

    public MaMeiTicketTableConfigEntity wrapToTicketTableConfigEntity(MaMeiTicketTableConfigDto dto) {
        return new MaMeiTicketTableConfigEntity(
                dto.getOwner(),
                dto.getCurrentFilterName(),
                dto.getCurrentTableId(),
                listToString(dto.getTableFilterIds().stream().map(object -> object.toString()).toList())
        );
    }


    public MaMeiTicketTableConfigDto wrapToTicketTableConfigDto(Optional<MaMeiTicketTableConfigEntity> entity) {
        if (!entity.isPresent()) {
            throw new NotFoundException("Entity not found");
        }
        return new MaMeiTicketTableConfigDto(
                entity.get().getOwner(),
                entity.get().getCurrentFilterName(),
                entity.get().getCurrentTableId(),
                stringToLongList(entity.get().getTableFilterIds())
        );
    }

    private static List<Long> stringToLongList(String value) {
        List<Long> result = new ArrayList<>();
        stringToList(value).stream().forEach(x -> result.add(Long.valueOf(x)));
        return result;
    }


    private static List<String> stringToList(String value) {
        try {
            return new ObjectMapper().readValue(value, ArrayList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listToString(List<String> list) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
