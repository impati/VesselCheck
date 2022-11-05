package com.example.vesselcheck.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 토큰을 응답해줌
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnTokenResponse {
    private String tokenType = "Bearer";
    private String  accessToken;
    private Integer  expiresIn;
    private String refreshToken;
    private Integer refreshTokenExpiresIn;
    private Boolean isOurClient;
    private String name;
    private String email;
}
