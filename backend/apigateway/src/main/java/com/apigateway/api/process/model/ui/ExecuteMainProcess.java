package com.apigateway.api.process.model.ui;

import com.apigateway.api.process.model.EProcessClassification;
import com.apigateway.api.process.model.EProcessEvent;
import com.apigateway.api.process.model.EProcessPlausibility;
import com.apigateway.api.process.model.EProcessTyp;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteMainProcess {

    private String signature;

    private String theme;

    private EProcessEvent processEvent;

    private EProcessTyp processTyp;

    private EProcessClassification processClassification;

    private EProcessPlausibility processPlausibility;

    private String processName;

    private String processText;

    private int mainProcessAmount;

    private List<ExecuteSubProcess>processList;

    private String time;

}
