package com.systemmanager.process.model.ui;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteProcessUI {

    private String signature;
    private List<ExecuteMainProcess>executeMainProcesses;
    private int mainProcessAmount;
    private String processDuration;

}
