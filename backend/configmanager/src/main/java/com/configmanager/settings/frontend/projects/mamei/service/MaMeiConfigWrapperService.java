package com.configmanager.settings.frontend.projects.mamei.service;

import com.configmanager.settings.frontend.projects.mamei.tickettable.MaMeiTicketTableConfigDto;
import com.configmanager.settings.frontend.projects.mamei.tickettable.MaMeiTicketTableConfigEntity;
import org.springframework.stereotype.Service;

@Service
public class MaMeiConfigWrapperService {

    public MaMeiTicketTableConfigEntity wrapToTicketTableConfigEntity(Object object) {
        MaMeiTicketTableConfigDto dto = (MaMeiTicketTableConfigDto) object;
        return wrapToTicketTableConfigEntity(dto);
    }

    public MaMeiTicketTableConfigEntity wrapToTicketTableConfigEntity(MaMeiTicketTableConfigDto dto) {
        return new MaMeiTicketTableConfigEntity(
                dto.getOwner(),
                dto.getCurrentFilterName(),
                dto.getCurrentTableId(),
                dto.getTableFilterIds()
        );
    }

}
