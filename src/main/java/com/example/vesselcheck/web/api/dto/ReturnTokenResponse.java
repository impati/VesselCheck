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
    private String token_type = "Bearer";
    private String  access_token;
    private Integer  expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private Boolean is_our_client;
    private String name;
    private String email;
}
