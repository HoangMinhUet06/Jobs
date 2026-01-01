package com.vehiclestore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vehiclestore.domain.dto.LoginDTO;

@RestController
public class AuthController {

    private final AuthenticationManagerBuilder authenticationMangerBuilder;

    public AuthController(AuthenticationManagerBuilder authenticationMangerBuilder) {
        this.authenticationMangerBuilder = authenticationMangerBuilder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) {

        // Load input include username and password to security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword());

        // Authentication user (need write function loadUserByUsername)
        Authentication authentication = authenticationMangerBuilder.getObject().authenticate(authenticationToken);
        return ResponseEntity.ok().body(loginDTO);
    }
}
