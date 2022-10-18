package com.example.vesselcheck.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 모든 요청에 기본적으로 필요함.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustRequest {
    private String access_token;
    private String refresh_token;
}
