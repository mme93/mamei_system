package com.apigateway.api.process.model.protocol;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_protocol")
public class TaskProtocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date executeTaskDate;
    private Date executeEndTaskDate;
    private String signature;
    private String mainProcessAmount;
    private String subProcessAmount;
    private String totalProcessAmount;
    private String processDuration;

    @Enumerated(EnumType.STRING)
    private ETaskStatus eTaskStatus;

    private String executeTaskUser;

    @Column(length = 1000)
    private String userComment;

    @OneToMany(mappedBy = "taskProtocol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessProtocol> processProtocols = new ArrayList<>();
}
