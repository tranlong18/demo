package com.example.demo.service;

import com.example.demo.dto.LoginDTO;

public interface LoginService {
    LoginDTO login (String email, String password);
}
