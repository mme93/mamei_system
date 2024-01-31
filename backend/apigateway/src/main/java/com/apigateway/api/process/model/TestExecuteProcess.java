package com.apigateway.api.process.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestExecuteProcess {

    private String signature;
    private String theme;
    private EProcessEvent processEvent;
    private EProcessTyp processTyp;
    private String processClassification;
    private String processPlausibility;
    private String processName;
    private String processText;
    private String time;

}
