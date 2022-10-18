package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class kakoLoginApiRestController {

    private final ClientService clientService;

    /**
     * 토큰 받기
     * @param returnTokenRequest
     * @return ReturnTokenResponse
     */
    @PostMapping("/get_token")
    public ReturnTokenResponse returnToken(@RequestBody ReturnTokenRequest returnTokenRequest){
        log.info("returnTokenRequest [{}]",returnTokenRequest.getCode());
        ReturnTokenResponse returnTokenResponse = getToken(returnTokenRequest.getCode());
        ResponseKakaoClient responseKakaoClient = getId(returnTokenResponse.getAccess_token());
        if(clientService.clientInfoBy(responseKakaoClient.getId()) == null) returnTokenResponse.is_our_client = false;
        else returnTokenResponse.is_our_client = true;
        return returnTokenResponse;
    }

    private ReturnTokenResponse getToken(String code){

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/token")
                .queryParam("grant_type","authorization_code")
                .queryParam("client_id",KakaoLogInConst.clientId)
                .queryParam("redirect_uri",KakaoLogInConst.redirectId)
                .queryParam("code",code)
                .queryParam("client_secret",KakaoLogInConst.clientSecret)
                .build()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST,uri);
        ResponseEntity<ReturnTokenResponse> result = new RestTemplate().exchange(requestEntity, ReturnTokenResponse.class);
        log.info("result [{}]",result.getBody());
        return result.getBody();
    }




    private ResponseKakaoClient getId(String token){
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com/v2/user/me")
                .build()
                .toUri();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + token);
        httpHeaders.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST,uri);
        ResponseEntity<ResponseKakaoClient> result = new RestTemplate().exchange(requestEntity, ResponseKakaoClient.class);
        log.info("id result = [{}]",result);
        return result.getBody();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ReturnTokenRequest{
        private String code;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ReturnTokenResponse{
        private String token_type = "Bearer";
        private String  access_token;
        private Integer  expires_in;
        private String refresh_token;
        private Integer refresh_token_expires_in;
        private Boolean is_our_client;
    }
}
