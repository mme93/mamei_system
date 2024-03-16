package com.systemmanager.token.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    private String token;

    public void setSignature() {
        if (!token.startsWith("Bearer")) {
            token = String.format("Bearer %s", token);
        }
    }

}
