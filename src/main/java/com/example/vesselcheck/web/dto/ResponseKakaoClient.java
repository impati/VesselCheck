package com.example.vesselcheck.web.dto;

import lombok.Data;
@Data
public class ResponseKakaoClient {
    private Long id;
    private KakaoAccount kakao_account;
    public Boolean isPossibleEmail(){
        if(kakao_account.getIs_email_valid() || kakao_account.getEmail_needs_agreement())return true;
        else return false;
    }
    public Boolean isPossibleNickName(){
        if(kakao_account.getProfile_nickname_needs_agreement())return true;
        else return false;
    }

}
