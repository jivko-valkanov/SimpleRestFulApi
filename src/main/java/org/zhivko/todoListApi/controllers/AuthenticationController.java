package org.zhivko.todoListApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zhivko.todoListApi.annotations.JsonRequestMapping;
import org.zhivko.todoListApi.configs.jwt.TokenProvider;
import org.zhivko.todoListApi.dtos.requests.LoginRequest;
import org.zhivko.todoListApi.dtos.responses.TokenResponse;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@JsonRequestMapping(path = "/auth")
public class AuthenticationController {

    private TokenProvider tokenProvider = null;

    private AuthenticationManager authenticationManager = null;

    @Autowired
    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @JsonRequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        try {

            Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);

            return ResponseEntity.ok(new TokenResponse(jwt));

        } catch (AuthenticationException ae) {
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                    ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @JsonRequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<String> me() {

        return new ResponseEntity<>("hahaha", HttpStatus.OK);
    }

}
