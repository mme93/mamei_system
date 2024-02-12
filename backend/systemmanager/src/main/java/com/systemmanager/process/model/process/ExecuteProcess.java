package com.systemmanager.process.model.process;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteProcess {

    private String signature;
    private String theme;
    private EProcessEvent processEvent;
    private EProcessTyp processTyp;
    private String processClassification;
    private String processPlausibility;
    private String processName;
    private String processText;
    private String time;
    private String taskSignature;

}
