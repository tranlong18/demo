package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TokenPayloadDTO {
    private String token;
    private String jwtId;
    private Date expiredTime;
}
