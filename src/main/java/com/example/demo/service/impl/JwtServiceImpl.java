package com.example.demo.service.impl;

import com.example.demo.dto.JwtInfoDTO;
import com.example.demo.dto.TokenPayloadDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.RedisToken;
import com.example.demo.repository.RedisTokenRepository;
import com.example.demo.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private RedisTokenRepository redisTokenRepository;

    @Override
    public TokenPayloadDTO generateToken(Account account, String type) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date issueTime = new Date();
        Date expiredTime = "access".equalsIgnoreCase(type)
                ? Date.from(issueTime.toInstant().plus(30, ChronoUnit.MINUTES))
                : Date.from(issueTime.toInstant().plus(15, ChronoUnit.DAYS));
        String jwtId = UUID.randomUUID().toString();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(account.getEmail())
                .issueTime(issueTime)
                .expirationTime(expiredTime)
                .jwtID(jwtId)
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        String token = jwsObject.serialize();
        return TokenPayloadDTO.builder()
                .token(token)
                .jwtId(jwtId)
                .expiredTime(expiredTime)
                .build();
    }

    public boolean verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expirationTime.before(new Date())) {
            return false;
        }
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Optional<RedisToken> byId = redisTokenRepository.findById(jwtId);
        if (byId.isPresent()) {
            throw new RuntimeException("TokenInvalid");
        }
        return signedJWT.verify(new MACVerifier(secretKey));
    }

    @Override
    public JwtInfoDTO parseToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date issueTime = signedJWT.getJWTClaimsSet().getIssueTime();
        Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        return JwtInfoDTO.builder()
                .jwtId(jwtId)
                .issueTime(issueTime)
                .expiredTime(expiredTime)
                .build();
    }
}
