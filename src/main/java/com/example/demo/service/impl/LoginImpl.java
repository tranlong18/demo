package com.example.demo.service.impl;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.Account;
import com.example.demo.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Override
    public LoginDTO login(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Account account = (Account) authentication.getPrincipal();
        String accessToken = jwtServiceImpl.generateToken(account,"access");
        String refreshToken = jwtServiceImpl.generateToken(account,"refresh");

        return new LoginDTO(accessToken,refreshToken);
    }
}
