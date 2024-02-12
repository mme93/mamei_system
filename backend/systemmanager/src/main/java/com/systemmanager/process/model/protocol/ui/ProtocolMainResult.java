package com.systemmanager.process.model.protocol.ui;

import com.systemmanager.process.model.protocol.EProcessStatus;
import com.systemmanager.process.model.protocol.EProcessTypProtocol;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolMainResult {

    private Long id;

    private String signature;

    private String parentSignature;

    private String processName;

    private String processText;

    private String executeProcessDate;

    private String executeEndProcessDate;

    private EProcessTypProtocol eProcessTypProtocol;

    private EProcessStatus eProcessStatus;

    private String result;

    private List<ProtocolSubResult>protocolSubResults;

}
