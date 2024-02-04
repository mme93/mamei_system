package com.apigateway.api.process.model.protocol.ui;

import com.apigateway.api.process.model.protocol.ETaskProcessStatus;
import com.apigateway.api.process.model.protocol.ProcessProtocol;
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
public class ProtocolResultUI {

    private Long id;

    private String executeTaskDate;

    private String executeEndTaskDate;

    private String signature;

    private String mainProcessAmount;

    private String subProcessAmount;

    private String totalProcessAmount;

    private String processDuration;

    private ETaskProcessStatus eTaskProcessStatus;

    private String executeTaskUser;

    private String userComment;

    private List<ProtocolMainResult> protocolMainResults;
}
