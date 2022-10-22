package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.domain.service.Dto.ClientInfo;
import com.example.vesselcheck.web.api.dto.*;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientApiRestController {

    private final ClientService clientService;

    /**
     * 토큰 받기
     * @param returnTokenRequest
     * @return ReturnTokenResponse
     */
    @PostMapping("/v1/get_token")
    public ReturnTokenResponse returnToken(@RequestBody ReturnTokenRequest returnTokenRequest){
        log.info("returnTokenRequest [{}]",returnTokenRequest.getCode());
        ReturnTokenResponse returnTokenResponse = getToken(returnTokenRequest.getCode());
        ResponseKakaoClient responseKakaoClient = KakaoLogInConst.getId(returnTokenResponse.getAccess_token());
        if(clientService.clientInfoBy(responseKakaoClient.getId()) == null) returnTokenResponse.setIs_our_client(false);
        else returnTokenResponse.setIs_our_client(true);
        returnTokenResponse.setName(responseKakaoClient.getKakao_account().getProfile().getNickname());
        returnTokenResponse.setEmail(responseKakaoClient.getKakao_account().getEmail());
        log.info("returnTokenResponse [{}]",returnTokenResponse);
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



    /**
     * 회원 가입시 기본 정보 내려주는  api
     */
    @GetMapping("/v1/join")
    public KakaoSimpleInfoResponse client_join(HttpServletRequest req){

        log.info("Authorization = [{}]",req.getHeader("Authorization"));
        String token = req.getHeader("Authorization");
        ResponseKakaoClient responseKakaoClient = KakaoLogInConst.getId(token);
        KakaoSimpleInfoResponse resp = new KakaoSimpleInfoResponse(responseKakaoClient.getKakao_account().getProfile().getNickname(), responseKakaoClient.getKakao_account().getEmail());
        log.info("resp = [{}]",resp);
        return resp;
    }

    /**
     *
     * 회원 가입
     */
    @PostMapping("/v1/join")
    public void client_infoSave(@RequestBody ClientSaveRequest clientSaveRequest,HttpServletRequest req){
        log.info("ClientSaveRequest [{}]",clientSaveRequest);
        clientService.clientRegister(clientSaveRequest.getName(),clientSaveRequest.getBelongs(),
                clientSaveRequest.getEmail(),clientSaveRequest.getDuty(),clientSaveRequest.getClient_type(),
                KakaoLogInConst.getId(req.getHeader("Authorization")).getId());
    }



    /**
     * 클라이언트 정보
     */
    @GetMapping("/v1/client")
    public ClientInfoResponse client_info(HttpServletRequest req){
        log.info("Authorization = [{}]",req.getHeader("Authorization"));
        String token = req.getHeader("Authorization");
        ClientInfo clientInfo = clientService.clientInfoBy(KakaoLogInConst.getId(token).getId());
        log.info("clientInfo = [{}]",clientInfo);
        ClientInfoResponse resp = new ClientInfoResponse(clientInfo.getName(), clientInfo.getEmail(), clientInfo.getBelongs(), clientInfo.getDuty(), clientInfo.getClientType());
        log.info("resp = [{}]",resp);
        return resp;
    }



}
