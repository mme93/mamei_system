package com.dashboard.ticket.model.entity;

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
@Table(name = "ticket_table_filter")
public class TicketTableFilterEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private String filterName;

    private boolean isCREATED;

    private boolean isWAITING;

    private boolean isREFINEMENT;

    private boolean isIN_PROGRESS;

    private boolean isDONE;

    @Column(length = 500, nullable = false)
    private String displayedColumns;

    public TicketTableFilterEntity(String filterName, boolean isCREATED, boolean isWAITING, boolean isREFINEMENT, boolean isIN_PROGRESS, boolean isDONE, String displayedColumns) {
        this.filterName = filterName;
        this.isCREATED = isCREATED;
        this.isWAITING = isWAITING;
        this.isREFINEMENT = isREFINEMENT;
        this.isIN_PROGRESS = isIN_PROGRESS;
        this.isDONE = isDONE;
        this.displayedColumns = displayedColumns;
    }
}
