package com.systemmanager.process.model.ui;

import com.systemmanager.process.model.process.EProcessClassification;
import com.systemmanager.process.model.process.EProcessEvent;
import com.systemmanager.process.model.process.EProcessPlausibility;
import com.systemmanager.process.model.process.EProcessTyp;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteSubProcess {

    private String signature;

    private String theme;

    private EProcessEvent processEvent;

    private EProcessTyp processTyp;

    private EProcessClassification processClassification;

    private EProcessPlausibility processPlausibility;

    private String processName;

    private String processText;

    private String time;
}
