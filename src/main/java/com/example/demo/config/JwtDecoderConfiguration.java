package com.example.demo.config;

import com.example.demo.service.impl.JwtServiceImpl;
import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Objects;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JwtDecoderConfiguration implements JwtDecoder {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    private NimbusJwtDecoder nimbusJwtDecoder = null;


    @Override
    public Jwt decode(String token) throws JwtException {
        log.info("Decoding JWT Token");
        try {
            if (!jwtServiceImpl.verifyToken(token)) {
                throw new RuntimeException("Invalid Token");
            }
            if (Objects.isNull(nimbusJwtDecoder)) {
                SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HS512");
                nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                        .macAlgorithm(MacAlgorithm.HS512)
                        .build();
            }
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
        return nimbusJwtDecoder.decode(token);
    }
}
