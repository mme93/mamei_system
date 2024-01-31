package com.apigateway.api.process.model.protocol;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

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

    public TaskProtocol(Date executeTaskDate, Date executeEndTaskDate, String signature, String mainProcessAmount, String subProcessAmount, String totalProcessAmount, String processDuration, ETaskStatus eTaskStatus, String executeTaskUser, String userComment, List<ProcessProtocol> processProtocols) {
        this.executeTaskDate = executeTaskDate;
        this.executeEndTaskDate = executeEndTaskDate;
        this.signature = signature;
        this.mainProcessAmount = mainProcessAmount;
        this.subProcessAmount = subProcessAmount;
        this.totalProcessAmount = totalProcessAmount;
        this.processDuration = processDuration;
        this.eTaskStatus = eTaskStatus;
        this.executeTaskUser = executeTaskUser;
        this.userComment = userComment;
        this.processProtocols = processProtocols;
    }
}
