package com.cjcaram.clients.controller;

import com.cjcaram.clients.util.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtils jwtUtil;

    public AuthController(JwtUtils jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/service-token")
    public String getServiceToken() {
        return jwtUtil.generateServiceToken();
    }
}

