package com.example.demo.service.impl;

import com.example.demo.dto.JwtInfoDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.TokenPayloadDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.RedisToken;
import com.example.demo.repository.RedisTokenRepository;
import com.example.demo.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Autowired
    private RedisTokenRepository redisTokenRepository;

    @Override
    public LoginDTO login(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Account account = (Account) authentication.getPrincipal();
        TokenPayloadDTO accessPayload = jwtServiceImpl.generateToken(account, "access");
        TokenPayloadDTO refreshPayload = jwtServiceImpl.generateToken(account, "refresh");
        if (refreshPayload.getJwtId() != null && refreshPayload.getToken() != null && refreshPayload.getExpiredTime() != null) {
            long ttlSeconds = (refreshPayload.getExpiredTime().getTime() - System.currentTimeMillis()) / 1000;
            redisTokenRepository.save(RedisToken.builder()
                    .jwtId(refreshPayload.getJwtId())
                    .expiredTime(ttlSeconds > 0 ? ttlSeconds : 0)
                    .build());
        }
        return new LoginDTO(accessPayload.getToken(), refreshPayload.getToken());
    }

    @Override
    public void logout(String token) throws ParseException {
        JwtInfoDTO jwtInfoDTO = jwtServiceImpl.parseToken(token);
        String jwtId = jwtInfoDTO.getJwtId();
        Date issueTime = jwtInfoDTO.getIssueTime();
        Date expiredTime = jwtInfoDTO.getExpiredTime();
        if (expiredTime.before(new Date())) {
            return;
        }
        RedisToken redisToken = RedisToken.builder()
                .jwtId(jwtId)
                .expiredTime(expiredTime.getTime() - issueTime.getTime())
                .build();
        redisTokenRepository.save(redisToken);
        log.info("logout success");
    }
}
