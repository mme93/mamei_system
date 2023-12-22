package com.securitygateway.security.model.dao.request;

import com.securitygateway.security.model.entity.UserFlags;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private UserFlags flag;
}
