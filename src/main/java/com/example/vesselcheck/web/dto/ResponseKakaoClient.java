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
        if(kakao_account.profile_nickname_needs_agreement)return true;
        else return false;
    }
    @Data
    static class KakaoAccount{
        private String email;
        private Boolean is_email_valid;
        private Boolean email_needs_agreement;
        private Boolean profile_nickname_needs_agreement;
        private Profile profile;
    }

    @Data
    static class Profile{
        private String nickname;
    }
}
