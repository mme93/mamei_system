package com.securitygateway.security.model.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationsRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String callNumber;
    private String username;
}
