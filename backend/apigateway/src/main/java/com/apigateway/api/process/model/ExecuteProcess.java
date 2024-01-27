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

    public ExecuteProcess(EProcessEvent processEvent, EProcessTyp processTyp, String processName, String processText, boolean hasDependedProcess, boolean isDependedProcess, String dependedProcessIds, String context) {
        this.processEvent = processEvent;
        this.processTyp = processTyp;
        this.processName = processName;
        this.processText = processText;
        this.hasDependedProcess = hasDependedProcess;
        this.isDependedProcess = isDependedProcess;
        this.dependedProcessIds = dependedProcessIds;
        this.context = context;
    }
}
