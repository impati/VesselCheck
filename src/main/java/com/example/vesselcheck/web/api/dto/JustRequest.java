package com.example.vesselcheck.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustRequest {
    private String access_token;
    private String refresh_token;
}
