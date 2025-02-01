package com.configmanager.settings.frontend.projects.mamei.tickettable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaMeiTicketTableConfigEntity {
    private String owner;
    private String currentFilterName;
    private Long currentTableId;
    private List<Long> tableFilterIds;
}
