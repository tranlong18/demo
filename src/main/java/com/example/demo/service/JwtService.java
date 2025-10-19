package com.example.demo.service;

import com.example.demo.entity.Account;

public interface JwtService {
    String generateToken(Account account, String type);
}
