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
    public JoinForm(ResponseUser responseUser) {
        this.name = responseUser.isPossibleNickName() ? responseUser.getKakao_account().getProfile().getNickname(): null;
        this.email = responseUser.isPossibleEmail() ? responseUser.getKakao_account().getEmail():null;
        this.kakaoId = responseUser.getId();
    }



}
