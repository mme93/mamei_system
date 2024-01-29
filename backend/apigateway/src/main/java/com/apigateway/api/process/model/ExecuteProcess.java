package com.apigateway.api.process.model;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteProcess {

    private EProcessEvent processEvent;

    private EProcessTyp processTyp;

    private EProcessClassification processClassification;

    private EProcessPlausibility processPlausibility;

    private String processName;

    private String processText;

    private boolean hasDependedProcess;

    private String dependedProcessIds;

    private String scopes;

    private String context;

    private String startTime;

    private String endTime;


}
