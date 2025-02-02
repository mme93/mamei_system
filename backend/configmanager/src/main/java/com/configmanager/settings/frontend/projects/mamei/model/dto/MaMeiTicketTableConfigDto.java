package com.configmanager.settings.frontend.projects.mamei.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaMeiTicketTableConfigDto {
    private String owner;
    private String currentFilterName;
    private Long currentTableId;
    private List<Long> tableFilterIds;
}
