package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.web.api.Exception.KaKaoAuthError;
import com.example.vesselcheck.web.api.dto.ReturnTokenResponse;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@Slf4j
public abstract class KakaoLogInConst {
    public final static String clientId = "299b0425b0382132eb1f239f7a0f4ae1";
    public final static String redirectId = "http://localhost:8080/client/add";//"http://localhost:3000/loading";
    public final static String clientSecret = "vEX71vGS4QzFBz2BFvdIFzfDEFot1fP3";
    public final static String logoutRedirect = "http://localhost:3000/logout";

    /**
        카카오에 사용자 정보를 요청
    */
    public static ResponseKakaoClient getId(String token){
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString("https://kapi.kakao.com/v2/user/me")
                    .build()
                    .toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Bearer " + token);
            httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST, uri);
            ResponseEntity<ResponseKakaoClient> result = new RestTemplate().exchange(requestEntity, ResponseKakaoClient.class);
            return result.getBody();
        }catch (Exception e){
            throw new KaKaoAuthError("카카오 인증 에러" , e);
        }
    }

    public static ReturnTokenResponse getToken(String code){
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString("https://kauth.kakao.com/oauth/token")
                    .queryParam("grant_type", "authorization_code")
                    .queryParam("client_id", KakaoLogInConst.clientId)
                    .queryParam("redirect_uri", KakaoLogInConst.redirectId)
                    .queryParam("code", code)
                    .queryParam("client_secret", KakaoLogInConst.clientSecret)
                    .build()
                    .toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST, uri);
            ResponseEntity<ReturnTokenResponse> result = new RestTemplate().exchange(requestEntity, ReturnTokenResponse.class);
            log.info("result [{}]", result.getBody());
            return result.getBody();
        }catch (Exception e){
            throw new KaKaoAuthError("카카로 토큰 에러 ",e);
        }
    }

}
