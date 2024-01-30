package com.apigateway.api.process.model.ui;

import com.apigateway.api.process.model.EProcessClassification;
import com.apigateway.api.process.model.EProcessEvent;
import com.apigateway.api.process.model.EProcessPlausibility;
import com.apigateway.api.process.model.EProcessTyp;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteSubProcess {

    private EProcessEvent processEvent;

    private EProcessTyp processTyp;

    private EProcessClassification processClassification;

    private EProcessPlausibility processPlausibility;

    private String processName;

    private String processText;

    private String context;

    private String startTime;

    private String endTime;
}
