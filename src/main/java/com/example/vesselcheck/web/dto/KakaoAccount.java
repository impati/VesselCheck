package com.example.vesselcheck.web.dto;

import lombok.Data;

@Data
public class KakaoAccount {
    private String email;
    private Boolean is_email_valid;
    private Boolean email_needs_agreement;
    private Boolean profile_nickname_needs_agreement;
    private Profile profile;

}
