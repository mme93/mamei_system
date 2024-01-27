package com.apigateway.api.process.model;

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

    private String processName;

    private String processText;

    private boolean hasDependedProcess;

    private boolean isDependedProcess;

    private String dependedProcessIds;

    private String context;

    private String startTime;

    private String endTime;
}
