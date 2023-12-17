package com.securitygateway.security.model.dao.request;

import lombok.*;

@Getter
@Setter
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
