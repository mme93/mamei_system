package com.systemmanager.process.model.protocol;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_process_protocol")
public class TaskProcessProtocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String executeTaskDate;

    private String executeEndTaskDate;

    @Column(unique = true)
    private String signature;

    private String mainProcessAmount;
    private String subProcessAmount;
    private String totalProcessAmount;
    private String processDuration;

    @Enumerated(EnumType.STRING)
    private ETaskProcessStatus eTaskProcessStatus;

    private String executeTaskUser;

    @Column(length = 1000)
    private String userComment;

    @OneToMany(mappedBy = "taskProcessProtocol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessProtocol> processProtocols = new ArrayList<>();

    public TaskProcessProtocol(String executeTaskDate, String executeEndTaskDate, String signature, String mainProcessAmount, String subProcessAmount, String totalProcessAmount, String processDuration, ETaskProcessStatus eTaskProcessStatus, String executeTaskUser, String userComment, List<ProcessProtocol> processProtocols) {
        this.executeTaskDate = executeTaskDate;
        this.executeEndTaskDate = executeEndTaskDate;
        this.signature = signature;
        this.mainProcessAmount = mainProcessAmount;
        this.subProcessAmount = subProcessAmount;
        this.totalProcessAmount = totalProcessAmount;
        this.processDuration = processDuration;
        this.eTaskProcessStatus = eTaskProcessStatus;
        this.executeTaskUser = executeTaskUser;
        this.userComment = userComment;
        this.processProtocols = processProtocols;
    }
}
