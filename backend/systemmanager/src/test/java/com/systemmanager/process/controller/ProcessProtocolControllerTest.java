package com.systemmanager.process.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemmanager.database.service.domains.ProcessDefaultDBService;
import com.systemmanager.process.model.protocol.*;
import com.systemmanager.process.model.protocol.ui.ProtocolMainResult;
import com.systemmanager.process.model.protocol.ui.ProtocolResultUI;
import com.systemmanager.process.model.protocol.ui.ProtocolSubResult;
import com.systemmanager.process.service.ProcessService;
import com.systemmanager.process.service.protocol.TaskProcessProtocolService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the ProcessProtocolController class.
 */
@SpringBootTest(classes = {ProcessProtocolController.class})
public class ProcessProtocolControllerTest {

    @Autowired
    @InjectMocks
    private ProcessProtocolController processProtocolController;

    @MockBean(name = "taskProcessProtocolService")
    private TaskProcessProtocolService taskProcessProtocolService;

    private MockMvc mockMvc;

    /**
     * Setup method to initialize the mockMvc and mockito annotations.
     */
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(processProtocolController)
                .build();
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test method for creating a task protocol.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testCreateTask() throws Exception {
        Mockito.doAnswer(invocation -> null).when(taskProcessProtocolService).createTaskProtocol(anyString(), anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/protocol/create/task_signature/currentUsername")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Test method for closing a task protocol.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testCloseTask() throws Exception {
        Mockito.doAnswer(invocation -> null).when(taskProcessProtocolService).closeTaskProtocol(anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/protocol/close/task_signature")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Test method for retrieving all task process protocols.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testGetTaskProcessProtocols() throws Exception {

        ProcessProtocol processProtocol = new ProcessProtocol(
                1L, "SIGNATURE", "PARENT-SIGNATURE", "ProcessName", "ProcessText",
                "ExecuteProcessDate", "ExecuteEndProcessDate",
                EProcessTypProtocol.MAIN, EProcessStatus.PASSED, "Result", null);

        TaskProcessProtocol taskProcessProtocol = new TaskProcessProtocol(1L, "ExecuteTaskDate",
                "ExecuteEndTaskDate", "SIGNATURE", "0", "0",
                "0", "0", ETaskProcessStatus.RESTRICTED, "ExecuteTaskUser",
                "UserComment", asList(processProtocol));

        Mockito.when(taskProcessProtocolService.getTaskProcessProtocols()).thenReturn(asList(taskProcessProtocol));

        mockMvc.perform(MockMvcRequestBuilders.get("/protocol/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(taskProcessProtocol.getId()))
                .andExpect(jsonPath("$[0].executeTaskDate").value(taskProcessProtocol.getExecuteTaskDate()))
                .andExpect(jsonPath("$[0].executeEndTaskDate").value(taskProcessProtocol.getExecuteEndTaskDate()))
                .andExpect(jsonPath("$[0].signature").value(taskProcessProtocol.getSignature()))
                .andExpect(jsonPath("$[0].mainProcessAmount").value(taskProcessProtocol.getMainProcessAmount()))
                .andExpect(jsonPath("$[0].subProcessAmount").value(taskProcessProtocol.getSubProcessAmount()))
                .andExpect(jsonPath("$[0].totalProcessAmount").value(taskProcessProtocol.getTotalProcessAmount()))
                .andExpect(jsonPath("$[0].processDuration").value(taskProcessProtocol.getProcessDuration()))
                .andExpect(jsonPath("$[0].executeTaskUser").value(taskProcessProtocol.getExecuteTaskUser()))
                .andExpect(jsonPath("$[0].userComment").value(taskProcessProtocol.getUserComment()))

                .andExpect(jsonPath("$[0].processProtocols[0].id").value(processProtocol.getId()))
                .andExpect(jsonPath("$[0].processProtocols[0].signature").value(processProtocol.getSignature()))
                .andExpect(jsonPath("$[0].processProtocols[0].parentSignature").value(processProtocol.getParentSignature()))
                .andExpect(jsonPath("$[0].processProtocols[0].processName").value(processProtocol.getProcessName()))
                .andExpect(jsonPath("$[0].processProtocols[0].processText").value(processProtocol.getProcessText()))
                .andExpect(jsonPath("$[0].processProtocols[0].executeProcessDate").value(processProtocol.getExecuteProcessDate()))
                .andExpect(jsonPath("$[0].processProtocols[0].executeEndProcessDate").value(processProtocol.getExecuteEndProcessDate()))
                .andExpect(jsonPath("$[0].processProtocols[0].result").value(processProtocol.getResult()))
                .andExpect(jsonPath("$[0].processProtocols[0].taskProcessProtocol").value(processProtocol.getTaskProcessProtocol()))
                .andExpect(jsonPath("$[0].processProtocols[0].eprocessTypProtocol").value(processProtocol.getEProcessTypProtocol().name()))
                .andExpect(jsonPath("$[0].processProtocols[0].eprocessStatus").value(processProtocol.getEProcessStatus().name()))

                .andExpect(jsonPath("$[0].etaskProcessStatus").value(taskProcessProtocol.getETaskProcessStatus().name()))
                .andReturn();
    }

    /**
     * Test method for loading protocols for a specific task.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testLoadProtocols() throws Exception {

        ProtocolSubResult protocolSubResult = new ProtocolSubResult(3L, "SIGNATURE", "PARENT-SIGNATURE",
                "ProcessName", "ProcessText", "ExecuteProcessDate",
                "ExecuteEndProcessDate", EProcessTypProtocol.MAIN, EProcessStatus.PASSED,
                "Result");

        ProtocolMainResult protocolMainResult = new ProtocolMainResult(2L, "SIGNATURE", "PARENT-SIGNATURE",
                "ProcessName", "ProcessText", "ExecuteProcessDate",
                "ExecuteEndProcessDate", EProcessTypProtocol.MAIN, EProcessStatus.PASSED,
                "Result", asList(protocolSubResult));

        ProtocolResultUI protocolResultUI = new ProtocolResultUI(1L, "ExecuteTaskDate",
                "ExecuteEndTaskDate", "SIGNATURE", "0", "0",
                "0", "0", ETaskProcessStatus.RESTRICTED, "ExecuteTaskUser",
                "UserComment", asList(protocolMainResult));

        Mockito.when(taskProcessProtocolService.getTaskProcessProtocol(anyString())).thenReturn(protocolResultUI);

        mockMvc.perform(MockMvcRequestBuilders.get("/protocol/load/task_signature")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(protocolResultUI.getId()))
                .andExpect(jsonPath("$.executeTaskDate").value(protocolResultUI.getExecuteTaskDate()))
                .andExpect(jsonPath("$.executeEndTaskDate").value(protocolResultUI.getExecuteEndTaskDate()))
                .andExpect(jsonPath("$.signature").value(protocolResultUI.getSignature()))
                .andExpect(jsonPath("$.mainProcessAmount").value(protocolResultUI.getMainProcessAmount()))
                .andExpect(jsonPath("$.subProcessAmount").value(protocolResultUI.getSubProcessAmount()))
                .andExpect(jsonPath("$.totalProcessAmount").value(protocolResultUI.getTotalProcessAmount()))
                .andExpect(jsonPath("$.processDuration").value(protocolResultUI.getProcessDuration()))
                .andExpect(jsonPath("$.executeTaskUser").value(protocolResultUI.getExecuteTaskUser()))
                .andExpect(jsonPath("$.userComment").value(protocolResultUI.getUserComment()))

                .andExpect(jsonPath("$.protocolMainResults[0].id").value(protocolMainResult.getId()))
                .andExpect(jsonPath("$.protocolMainResults[0].signature").value(protocolMainResult.getSignature()))
                .andExpect(jsonPath("$.protocolMainResults[0].parentSignature").value(protocolMainResult.getParentSignature()))
                .andExpect(jsonPath("$.protocolMainResults[0].processName").value(protocolMainResult.getProcessName()))
                .andExpect(jsonPath("$.protocolMainResults[0].processText").value(protocolMainResult.getProcessText()))
                .andExpect(jsonPath("$.protocolMainResults[0].executeProcessDate").value(protocolMainResult.getExecuteProcessDate()))
                .andExpect(jsonPath("$.protocolMainResults[0].executeEndProcessDate").value(protocolMainResult.getExecuteEndProcessDate()))
                .andExpect(jsonPath("$.protocolMainResults[0].result").value(protocolMainResult.getResult()))

                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].id").value(protocolSubResult.getId()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].signature").value(protocolSubResult.getSignature()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].parentSignature").value(protocolSubResult.getParentSignature()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].processName").value(protocolSubResult.getProcessName()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].processText").value(protocolSubResult.getProcessText()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].executeProcessDate").value(protocolSubResult.getExecuteProcessDate()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].executeEndProcessDate").value(protocolSubResult.getExecuteEndProcessDate()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].result").value(protocolSubResult.getResult()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].eprocessTypProtocol").value(protocolSubResult.getEProcessTypProtocol().name()))
                .andExpect(jsonPath("$.protocolMainResults[0].protocolSubResults[0].eprocessStatus").value(protocolSubResult.getEProcessStatus().name()))

                .andExpect(jsonPath("$.protocolMainResults[0].eprocessTypProtocol").value(protocolMainResult.getEProcessTypProtocol().name()))
                .andExpect(jsonPath("$.protocolMainResults[0].eprocessStatus").value(protocolMainResult.getEProcessStatus().name()))

                .andExpect(jsonPath("$.etaskProcessStatus").value(protocolResultUI.getETaskProcessStatus().name()))

                .andReturn();
    }

    /**
     * Test method for updating user comment for a protocol.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testUpdateUserComment() throws Exception {
        ProtocolUserComment userComment= new ProtocolUserComment("UserComment","Task-SIGNATURE");
        String json = new ObjectMapper().writeValueAsString(userComment);
        Mockito.doAnswer(invocation -> null).when(taskProcessProtocolService).updateUserComment(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/protocol/comment/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}
