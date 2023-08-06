package com.login.service;


import com.login.dao.request.SignUpRequest;
import com.login.dao.request.SigninRequest;
import com.login.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
