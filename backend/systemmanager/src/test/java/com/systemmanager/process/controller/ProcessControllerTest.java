package com.systemmanager.process.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemmanager.database.service.domains.ProcessDefaultDBService;
import com.systemmanager.process.model.process.*;
import com.systemmanager.process.model.ui.ExecuteMainProcess;
import com.systemmanager.process.model.ui.ExecuteProcessUI;
import com.systemmanager.process.model.ui.ExecuteSubProcess;
import com.systemmanager.process.model.ui.ProcessElementUI;
import com.systemmanager.process.service.ProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the ProcessController class.
 */
@SpringBootTest(classes = {ProcessController.class})
public class ProcessControllerTest {

    @Autowired
    @InjectMocks
    private ProcessController processController;

    @MockBean(name = "processService")
    private ProcessService processService;

    @MockBean(name = "processDefaultDBService")
    private ProcessDefaultDBService processDefaultDBService;

    private MockMvc mockMvc;

    /**
     * Setup method to initialize the mockMvc and mockito annotations.
     */
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(processController)
                .build();
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Test case to verify that starting a new job returns HTTP status OK.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    void testStartNewJobWithHttpStatusOk() throws Exception {
        ExecuteProcess process = new ExecuteProcess(null, null, null, null,
                null, null, null, null, null, null);
        Mockito.when(processService.startProcess(any())).thenReturn(true);
        String json = new ObjectMapper().writeValueAsString(process);
        mockMvc.perform(MockMvcRequestBuilders.post("/process/newJob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true))
                .andReturn();
    }

    /**
     * Test case to verify that retrieving process list returns HTTP status OK.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    void testGetProcessListWithHttpStatusOk() throws Exception {
        ProcessElementUI processElementUI = new ProcessElementUI(
                1L,
                EProcessEvent.RESET,
                EProcessTyp.DATA_SET,
                EProcessClassification.CRITICAL,
                EProcessPlausibility.PLAUSIBLE,
                "Test Process",
                "Test Process Text",
                false,
                asList("5"),
                asList("TestTable")
        );
        Mockito.when(processService.getProcessElementUI()).thenReturn(asList(processElementUI));
        mockMvc.perform(MockMvcRequestBuilders.get("/process/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(processElementUI.getId()))
                .andExpect(jsonPath("$.[0].processEvent").value(processElementUI.getProcessEvent().name()))
                .andExpect(jsonPath("$.[0].processTyp").value(processElementUI.getProcessTyp().name()))
                .andExpect(jsonPath("$.[0].processClassification").value(processElementUI.getProcessClassification().name()))
                .andExpect(jsonPath("$.[0].processPlausibility").value(processElementUI.getProcessPlausibility().name()))
                .andExpect(jsonPath("$.[0].processName").value(processElementUI.getProcessName()))
                .andExpect(jsonPath("$.[0].processText").value(processElementUI.getProcessText()))
                .andExpect(jsonPath("$.[0].hasDependedProcess").value(processElementUI.isHasDependedProcess()))
                .andExpect(jsonPath("$.[0].dependedProcessIds[0]").value(processElementUI.getDependedProcessIds().get(0)))
                .andExpect(jsonPath("$.[0].scopeList[0]").value(processElementUI.getScopeList().get(0)))
                .andReturn();
    }

    /**
     * Test case to verify that creating a sorted ExecuteProcessUI returns HTTP status OK.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    void testCreateSortedExecuteProcessUIHttpStatusOk() throws Exception {
        ExecuteSubProcess executeSubProcess = new ExecuteSubProcess("SIGNATURE", "TestTable",
                EProcessEvent.CREATE, EProcessTyp.DATA_SET, EProcessClassification.CRITICAL, EProcessPlausibility.NONE,
                "Test Process Name", "Test Process Text", null);
        ExecuteMainProcess executeMainProcess = new ExecuteMainProcess("SIGNATURE", "TestTable",
                EProcessEvent.CREATE, EProcessTyp.DATA_SET, EProcessClassification.CRITICAL, EProcessPlausibility.NONE,
                "Test Process Name", "Test Process Text", 0, asList(executeSubProcess), null);
        ExecuteProcessUI executeProcessUI = new ExecuteProcessUI("SIGNATURE", asList(executeMainProcess), 0, "0");

        ProcessElementUI processElementUI = new ProcessElementUI(1L, EProcessEvent.CREATE, EProcessTyp.DATA_SET,
                EProcessClassification.CRITICAL, EProcessPlausibility.NONE,
                "Test Process Name", "Test Process Text",true,asList("2"),asList("TestTheme"));

        Mockito.when(processService.createSortedExecuteProcessUI(any())).thenReturn(executeProcessUI);
        String json = new ObjectMapper().writeValueAsString(asList(processElementUI));
        mockMvc.perform(MockMvcRequestBuilders.put("/process/sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.signature").value(executeProcessUI.getSignature()))

                .andExpect(jsonPath("$.executeMainProcesses[0].signature").value(executeMainProcess.getSignature()))
                .andExpect(jsonPath("$.executeMainProcesses[0].theme").value(executeMainProcess.getTheme()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processEvent").value(executeMainProcess.getProcessEvent().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processTyp").value(executeMainProcess.getProcessTyp().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processClassification").value(executeMainProcess.getProcessClassification().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processPlausibility").value(executeMainProcess.getProcessPlausibility().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processName").value(executeMainProcess.getProcessName()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processText").value(executeMainProcess.getProcessText()))
                .andExpect(jsonPath("$.executeMainProcesses[0].mainProcessAmount").value(executeMainProcess.getMainProcessAmount()))

                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].signature").value(executeSubProcess.getSignature()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].theme").value(executeSubProcess.getTheme()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].processEvent").value(executeSubProcess.getProcessEvent().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].processTyp").value(executeSubProcess.getProcessTyp().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].processClassification").value(executeSubProcess.getProcessClassification().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].processPlausibility").value(executeSubProcess.getProcessPlausibility().name()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].processName").value(executeSubProcess.getProcessName()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].processText").value(executeSubProcess.getProcessText()))
                .andExpect(jsonPath("$.executeMainProcesses[0].processList[0].time").value(executeSubProcess.getTime()))

                .andExpect(jsonPath("$.mainProcessAmount").value(executeProcessUI.getMainProcessAmount()))
                .andExpect(jsonPath("$.processDuration").value(executeProcessUI.getProcessDuration()))

                .andReturn();
    }

    /**
     * Test case to verify that setting default data returns HTTP status OK.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    void testSetDefaultHttpStatusOk() throws Exception {
        Mockito.when(processDefaultDBService.deleteAllDefaultData()).thenReturn(true);
        Mockito.when(processDefaultDBService.loadDefaultDataIntoDatabase()).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/process/set_default")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
