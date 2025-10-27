package com.example.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtInfoDTO implements Serializable {
    private String jwtId;
    private Date issueTime;
    private Date expiredTime;
}
