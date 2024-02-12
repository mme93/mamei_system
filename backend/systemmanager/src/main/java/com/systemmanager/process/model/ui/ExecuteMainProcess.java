package com.systemmanager.process.model.ui;

import com.systemmanager.process.model.process.EProcessClassification;
import com.systemmanager.process.model.process.EProcessEvent;
import com.systemmanager.process.model.process.EProcessPlausibility;
import com.systemmanager.process.model.process.EProcessTyp;
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
