package com.example.vesselcheck.web.dto;

import com.example.vesselcheck.domain.entity.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinForm {

    private String name;
    private String belongs;
    private String email;
    private ClientType clientType;
    private Long kakaoId;
    public JoinForm(ResponseKakaoClient responseKakaoClient) {
        this.name = responseKakaoClient.isPossibleNickName() ? responseKakaoClient.getKakao_account().getProfile().getNickname(): null;
        this.email = responseKakaoClient.isPossibleEmail() ? responseKakaoClient.getKakao_account().getEmail():null;
        this.kakaoId = responseKakaoClient.getId();
    }



}
