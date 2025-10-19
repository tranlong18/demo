package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    LoginDTO login(@RequestBody Map<String, String> request){
        String email = request.get("email");
        String password = request.get("password");
        return loginService.login(email, password);
    }
}
