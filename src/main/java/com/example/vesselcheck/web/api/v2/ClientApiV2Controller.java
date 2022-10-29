package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.web.api.Exception.KaKaoAuthError;
import com.example.vesselcheck.web.api.dto.ErrorResult;
import com.example.vesselcheck.web.api.dto.ReturnTokenRequest;
import com.example.vesselcheck.web.api.dto.ReturnTokenResponse;
import com.example.vesselcheck.web.api.v1.KakaoLogInConst;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.vesselcheck.web.api.v1.KakaoLogInConst.getId;
import static com.example.vesselcheck.web.api.v1.KakaoLogInConst.getToken;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientApiV2Controller {
    private final ClientService clientService;
    @ExceptionHandler(KaKaoAuthError.class)
    public ErrorResult kakaoAuth(KaKaoAuthError e){
        return new ErrorResult("Bad Request",e.getMessage());
    }
    /**
     * 토큰 받기
     */
    @PostMapping("/v2/get_token")
    public ReturnTokenResponse returnToken(@RequestBody ReturnTokenRequest returnTokenRequest){
        ReturnTokenResponse returnTokenResponse = getToken(returnTokenRequest.getCode());
        ResponseKakaoClient responseKakaoClient = getId(returnTokenResponse.getAccess_token());
        if(clientService.clientInfoBy(responseKakaoClient.getId()) == null) returnTokenResponse.setIs_our_client(false);
        else returnTokenResponse.setIs_our_client(true);
        returnTokenResponse.setName(responseKakaoClient.getKakao_account().getProfile().getNickname());
        returnTokenResponse.setEmail(responseKakaoClient.getKakao_account().getEmail());
        log.info("returnTokenResponse [{}]",returnTokenResponse);
        return returnTokenResponse;
    }









}
