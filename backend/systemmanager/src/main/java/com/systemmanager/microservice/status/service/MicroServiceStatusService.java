package com.systemmanager.microservice.status.service;

import com.systemmanager.microservice.restart.service.MicroServicesRestartService;
import com.systemmanager.microservice.status.model.dto.EServiceStatus;
import com.systemmanager.microservice.status.model.dto.MicroServiceDto;
import com.systemmanager.microservice.status.model.entity.MicroServiceEntity;
import com.systemmanager.microservice.status.repository.MicroServiceRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MicroServiceStatusService {

    private final DiscoveryClient discoveryClient;
    private final MicroServiceRepository microServiceRepository;

    public List<MicroServiceDto> getMicroServicesStatus() {
        List<MicroServiceDto>microServiceDtos= new ArrayList<>();
        List<String>serviceNames=discoveryClient.getServices();
        serviceNames.add("system");
        for(MicroServiceEntity microServiceEntity:microServiceRepository.findAll()){
            if(serviceNames.contains(microServiceEntity.getEurekaServiceName().toLowerCase(Locale.ROOT))){
                microServiceDtos.add(new MicroServiceDto(microServiceEntity.getEurekaServiceName(),
                        microServiceEntity.getIconName(),
                        microServiceEntity.getText(),
                        microServiceEntity.getDescription(),
                        EServiceStatus.ONLINE));
            }else{
                microServiceDtos.add(new MicroServiceDto(microServiceEntity.getEurekaServiceName(),
                        microServiceEntity.getIconName(),
                        microServiceEntity.getText(),
                        microServiceEntity.getDescription(),
                        EServiceStatus.OFFLINE));
            }
        }
        return microServiceDtos;
    }

    public MicroServiceDto getMicroServiceStatus(String microServiceName) {
        Optional<MicroServiceEntity>microServiceEntityOpt=microServiceRepository.findByEurekaServiceName(microServiceName);
        if(microServiceEntityOpt.isPresent()){
            MicroServiceEntity microServiceEntity=microServiceEntityOpt.get();
            EServiceStatus status;
            if(discoveryClient.getServices().contains(microServiceEntity.getEurekaServiceName().toLowerCase(Locale.ROOT))){
                status= EServiceStatus.ONLINE;
            }else{
                status= EServiceStatus.OFFLINE;
            }
            return new MicroServiceDto(microServiceEntity.getId(),
                    microServiceEntity.getEurekaServiceName(),microServiceEntity.getIconName(),
                    microServiceEntity.getText(),
                    microServiceEntity.getDescription(),
                    status);

        }
        throw new NotFoundException("Micro Service by Name not found: "+microServiceName);
    }
}
