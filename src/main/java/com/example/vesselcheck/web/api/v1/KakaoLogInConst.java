package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class KakaoLogInConst {
    public final static String clientId = "299b0425b0382132eb1f239f7a0f4ae1";
    public final static String redirectId = "http://localhost:3000/loading";
    public final static String clientSecret = "vEX71vGS4QzFBz2BFvdIFzfDEFot1fP3";
    public final static String logoutRedirect = "http://localhost:3000/logout";

    /**
        카카오에 사용자 정보를 요청
    */
    public static ResponseKakaoClient getId(String token){
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com/v2/user/me")
                .build()
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + token);
        httpHeaders.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST,uri);
        ResponseEntity<ResponseKakaoClient> result = new RestTemplate().exchange(requestEntity, ResponseKakaoClient.class);
        return result.getBody();
    }

}
