package com.systemmanager.process.model.protocol.ui;

import com.systemmanager.process.model.protocol.ETaskProcessStatus;
import lombok.*;

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
