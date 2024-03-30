package com.systemmanager.microservice.status;

import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import com.systemmanager.microservice.status.controller.MicroServiceStatusController;
import com.systemmanager.microservice.status.model.dto.EServiceStatus;
import com.systemmanager.microservice.status.model.dto.MicroServiceDto;
import com.systemmanager.microservice.status.service.MicroServiceStatusService;
import jakarta.ws.rs.NotFoundException;
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
 * Unit tests for the MicroServiceStatusController class.
 */
@SpringBootTest(classes = {MicroServiceStatusController.class})
public class MicroServiceStatusControllerTest {

    @Autowired
    @InjectMocks
    private MicroServiceStatusController microServiceStatusController;

    @MockBean(name = "microServiceStatusService")
    private MicroServiceStatusService microServiceStatusService;

    private MockMvc mockMvc;

    private MicroServiceDto microServiceDtoOnline;

    private MicroServiceDto microServiceDtoOffline;

    /**
     * Setup method to initialize the mockMvc and mockito annotations.
     */
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(microServiceStatusController)
                .build();
        MockitoAnnotations.openMocks(this);

        microServiceDtoOnline = new MicroServiceDto(
                1L,
                EurekaDiscoveryClientNameTable.ApiGateWay,
                "icon",
                "testText",
                "testDescription",
                EServiceStatus.ONLINE
        );
        microServiceDtoOffline = new MicroServiceDto(
                2L,
                EurekaDiscoveryClientNameTable.DataStorageAPI,
                "icon",
                "testText",
                "testDescription",
                EServiceStatus.OFFLINE
        );
    }

    /**
     * Test case to verify the successful retrieval of microservices status with HTTP status OK.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetMicroServicesStatusWithHttpStatusOk() throws Exception {
        Mockito.
                when(microServiceStatusService.getMicroServicesStatus()).
                thenReturn(asList(microServiceDtoOnline, microServiceDtoOffline));
        mockMvc.perform(MockMvcRequestBuilders.get("/service_status")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(microServiceDtoOnline.getId()))
                .andExpect(jsonPath("$.[0].eurekaServiceName").value(microServiceDtoOnline.getEurekaServiceName()))
                .andExpect(jsonPath("$.[0].iconName").value(microServiceDtoOnline.getIconName()))
                .andExpect(jsonPath("$.[0].text").value(microServiceDtoOnline.getText()))
                .andExpect(jsonPath("$.[0].description").value(microServiceDtoOnline.getDescription()))
                .andExpect(jsonPath("$.[0].status").value(microServiceDtoOnline.getStatus().name()))
                .andExpect(jsonPath("$.[1].id").value(microServiceDtoOffline.getId()))
                .andExpect(jsonPath("$.[1].eurekaServiceName").value(microServiceDtoOffline.getEurekaServiceName()))
                .andExpect(jsonPath("$.[1].iconName").value(microServiceDtoOffline.getIconName()))
                .andExpect(jsonPath("$.[1].text").value(microServiceDtoOffline.getText()))
                .andExpect(jsonPath("$.[1].description").value(microServiceDtoOffline.getDescription()))
                .andExpect(jsonPath("$.[1].status").value(microServiceDtoOffline.getStatus().name()))
                .andReturn();
    }

    /**
     * Test case to verify the successful retrieval of a microservice status with HTTP status OK.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetMicroServiceStatusWithHttpStatusOk() throws Exception {
        Mockito.
                when(microServiceStatusService.getMicroServiceStatus(any())).
                thenReturn(microServiceDtoOnline);
        mockMvc.perform(MockMvcRequestBuilders.get("/service_status/microServiceName")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(microServiceDtoOnline.getId()))
                .andExpect(jsonPath("$.eurekaServiceName").value(microServiceDtoOnline.getEurekaServiceName()))
                .andExpect(jsonPath("$.iconName").value(microServiceDtoOnline.getIconName()))
                .andExpect(jsonPath("$.text").value(microServiceDtoOnline.getText()))
                .andExpect(jsonPath("$.description").value(microServiceDtoOnline.getDescription()))
                .andExpect(jsonPath("$.status").value(microServiceDtoOnline.getStatus().name()))
                .andReturn();
    }

    /**
     * Test case to verify the unsuccessful retrieval of a microservice status with HTTP status Not Found.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetMicroServiceStatusWithHttpStatusNotFound() throws Exception {
        Mockito.
                when(microServiceStatusService.getMicroServiceStatus(any())).
                thenThrow(NotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/service_status/microServiceName")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
