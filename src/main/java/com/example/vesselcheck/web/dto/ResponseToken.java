package com.example.vesselcheck.web.dto;

import lombok.Data;

@Data
public class ResponseToken {
    private String token_type;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
}
