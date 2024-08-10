package com.example.jwt_auth2.service;

import com.example.jwt_auth2.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String createToken(String username) {
        return jwtTokenUtil.generateToken(username);
    }

    public boolean validateToken(String token, String username) {
        return jwtTokenUtil.validateToken(token, username);
    }

}
