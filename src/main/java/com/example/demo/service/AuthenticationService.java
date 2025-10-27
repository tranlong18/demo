package com.example.demo.service;

import com.example.demo.dto.LoginDTO;

import java.text.ParseException;

public interface AuthenticationService {
    LoginDTO login (String email, String password);

    void logout (String token) throws ParseException;
}
