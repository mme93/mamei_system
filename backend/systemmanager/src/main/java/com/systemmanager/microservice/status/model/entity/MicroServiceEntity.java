package com.systemmanager.microservice.status.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "micro_service")
public class MicroServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eurekaServiceName;

    @Column(nullable = false)
    private String iconName;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String description;

    public MicroServiceEntity(String eurekaServiceName, String iconName, String text, String description) {
        this.eurekaServiceName = eurekaServiceName;
        this.iconName = iconName;
        this.text = text;
        this.description = description;
    }
}
