package com.systemmanager.microservice.status.service;

import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import com.systemmanager.microservice.status.model.dto.EServiceStatus;
import com.systemmanager.microservice.status.model.dto.MicroServiceDto;
import com.systemmanager.microservice.status.model.entity.MicroServiceEntity;
import com.systemmanager.microservice.status.repository.MicroServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Unit tests for the MicroServiceStatusService class.
 */
@SpringBootTest(classes = {MicroServiceStatusService.class})
public class MicroServiceStatusServiceTest {

    @Autowired
    @InjectMocks
    private MicroServiceStatusService microServiceStatusService;

    @MockBean(name = "microServiceRepository")
    private MicroServiceRepository microServiceRepository;

    private MicroServiceEntity microServiceEntityOnline;

    private MicroServiceEntity microServiceEntityOffline;

    @MockBean
    private DiscoveryClient discoveryClientMock;

    /**
     * Initializes Mockito annotations before each test method.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        discoveryClientMock = Mockito.mock(DiscoveryClient.class);

        microServiceEntityOnline = new MicroServiceEntity(
                1L,
                EurekaDiscoveryClientNameTable.SystemManagerAPI,
                "icon",
                "testText",
                "testDescription"
        );
        microServiceEntityOffline = new MicroServiceEntity(
                2L,
                EurekaDiscoveryClientNameTable.DataStorageAPI,
                "icon",
                "testText",
                "testDescription"
        );
    }

    /**
     * Test case to verify the retrieval of microservices status.
     */
    @Test
    void testGetMicroServicesStatus() {
        Mockito.when(discoveryClientMock.getServices()).thenReturn(Collections.emptyList());
        Mockito.when(microServiceRepository.findAll()).thenReturn(asList(microServiceEntityOnline, microServiceEntityOffline));
        List<MicroServiceDto> resultList = microServiceStatusService.getMicroServicesStatus();
        Assertions.assertEquals(resultList.size(), 2);
        Assertions.assertEquals(resultList.get(0).getEurekaServiceName(), EurekaDiscoveryClientNameTable.SystemManagerAPI);
        Assertions.assertEquals(resultList.get(0).getStatus(), EServiceStatus.ONLINE);
        Assertions.assertEquals(resultList.get(1).getEurekaServiceName(), EurekaDiscoveryClientNameTable.DataStorageAPI);
        Assertions.assertEquals(resultList.get(1).getStatus(), EServiceStatus.OFFLINE);
    }

    /**
     * Test case to verify the scenario when no microservices are found.
     */
    @Test
    void testNoFoundMicroServicesStatus() {
        Mockito.when(discoveryClientMock.getServices()).thenReturn(Collections.emptyList());
        Mockito.when(microServiceRepository.findAll()).thenReturn(Collections.emptyList());
        List<MicroServiceDto> resultList = microServiceStatusService.getMicroServicesStatus();
        Assertions.assertEquals(resultList.size(), 0);
    }

    /**
     * Test case to verify the retrieval of a microservice status when it is offline.
     */
    @Test
    void testGetMicroServiceStatusOffline() {
        Mockito.when(discoveryClientMock.getServices()).thenReturn(asList(EurekaDiscoveryClientNameTable.DataStorageAPI));
        Mockito.when(microServiceRepository.findByEurekaServiceName(anyString())).thenReturn(Optional.of(microServiceEntityOffline));
        MicroServiceDto result = microServiceStatusService.getMicroServiceStatus(EurekaDiscoveryClientNameTable.DataStorageAPI);
        Assertions.assertEquals(result.getStatus(), EServiceStatus.OFFLINE);
    }

    /**
     * Test case to verify the scenario when no microservice is found.
     */
    @Test
    void testNoFoundMicroServiceStatus() {
        Mockito.when(discoveryClientMock.getServices()).thenReturn(Collections.emptyList());
        Mockito.when(microServiceRepository.findByEurekaServiceName(anyString())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(RuntimeException.class, () -> {
            microServiceStatusService.getMicroServiceStatus(EurekaDiscoveryClientNameTable.DataStorageAPI);
        });
    }
}
