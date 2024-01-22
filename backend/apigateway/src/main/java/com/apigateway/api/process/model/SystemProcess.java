package com.apigateway.api.process.model;

import java.util.List;

public class SystemProcess {

    private EProcessTyp processTyp;
    private EProcessMods processMods;
    private String processText;
    private String processIdentification;
    private List<SystemProcess>subSystemProcess;
}
