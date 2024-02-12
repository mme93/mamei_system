package com.systemmanager.microservice.status.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MicroServiceDto {

    private Long id;

    private String eurekaServiceName;

    private String iconName;

    private String text;

    private String description;

    private EServiceStatus status;

    public MicroServiceDto(String eurekaServiceName, String iconName, String text, String description, EServiceStatus status) {
        this.eurekaServiceName = eurekaServiceName;
        this.iconName = iconName;
        this.text = text;
        this.description = description;
        this.status = status;
    }

    public MicroServiceDto(String eurekaServiceName, String iconName, String text, String description) {
        this.eurekaServiceName = eurekaServiceName;
        this.iconName = iconName;
        this.text = text;
        this.description = description;
    }
}
