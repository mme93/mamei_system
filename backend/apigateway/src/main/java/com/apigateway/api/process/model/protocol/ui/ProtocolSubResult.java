package com.apigateway.api.process.model.protocol.ui;


import com.apigateway.api.process.model.protocol.EProcessStatus;
import com.apigateway.api.process.model.protocol.EProcessTypProtocol;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolSubResult {

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

}
