package com.example.demo.service;

import com.example.demo.dto.JwtInfoDTO;
import com.example.demo.dto.TokenPayloadDTO;
import com.example.demo.entity.Account;

import java.text.ParseException;

public interface JwtService {
    TokenPayloadDTO generateToken(Account account, String type);
    JwtInfoDTO parseToken(String token) throws ParseException;
}
