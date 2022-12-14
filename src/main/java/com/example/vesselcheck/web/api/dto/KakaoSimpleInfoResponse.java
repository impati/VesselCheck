package com.example.vesselcheck.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 가입 시 카카오 정보를 내려주는 dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoSimpleInfoResponse {
    private String name;
    private String email;
}
