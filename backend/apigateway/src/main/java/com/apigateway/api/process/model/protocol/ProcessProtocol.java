package com.apigateway.api.process.model.protocol;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "process_protocol")
public class ProcessProtocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String signature;
    private String parent_signature;
    private String processName;
    private String processText;
    private String executeProcessDate;
    private String executeEndProcessDate;

    @Enumerated(EnumType.STRING)
    private EProcessTypProtocol eProcessTypProtocol;

    @Enumerated(EnumType.STRING)
    private EProcessStatus eProcessStatus;

    @Column(length = 1000)
    private String result;

    @ManyToOne
    @JoinColumn(name = "task_protocol_id")
    private TaskProcessProtocol taskProcessProtocol;
}
