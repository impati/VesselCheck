package com.example.vesselcheck.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 토큰을 얻기 위한 요청
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnTokenRequest {
        private String code;
}
