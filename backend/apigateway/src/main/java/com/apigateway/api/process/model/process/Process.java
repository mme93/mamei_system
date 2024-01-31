package com.apigateway.api.process.model.process;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EProcessEvent processEvent;

    @Enumerated(EnumType.STRING)
    private EProcessTyp processTyp;

    @Enumerated(EnumType.STRING)
    private EProcessClassification processClassification;

    @Enumerated(EnumType.STRING)
    private EProcessPlausibility processPlausibility;

    @Column(unique = true, nullable = false)
    private String processName;

    private String processText;

    private boolean hasDependedProcess;

    @Column(length = 1000)
    private String dependedProcessIds;

    @Column(length = 1000)
    private String scopes;

    public Process(EProcessEvent processEvent, EProcessTyp processTyp, EProcessClassification processClassification, EProcessPlausibility processPlausibility, String processName, String processText, boolean hasDependedProcess, String dependedProcessIds, String scopes) {
        this.processEvent = processEvent;
        this.processTyp = processTyp;
        this.processClassification = processClassification;
        this.processPlausibility = processPlausibility;
        this.processName = processName;
        this.processText = processText;
        this.hasDependedProcess = hasDependedProcess;
        this.dependedProcessIds = dependedProcessIds;
        this.scopes = scopes;
    }
}
