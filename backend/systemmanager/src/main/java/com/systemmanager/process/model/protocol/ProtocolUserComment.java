package com.systemmanager.process.model.protocol;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolUserComment {

    private String userComment;
    private String taskSignature;

}
