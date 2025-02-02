package com.configmanager.settings.frontend.projects.mamei.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mamei_ticket_table_config")
public class MaMeiTicketTableConfigEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String owner;

    private String currentFilterName;

    private Long currentTableId;

    private String tableFilterIds;

    public MaMeiTicketTableConfigEntity(String owner, String currentFilterName, Long currentTableId, String tableFilterIds) {
        this.owner = owner;
        this.currentFilterName = currentFilterName;
        this.currentTableId = currentTableId;
        this.tableFilterIds = tableFilterIds;
    }
}
