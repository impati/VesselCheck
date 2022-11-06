package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.domain.service.Dto.ClientInfo;
import com.example.vesselcheck.web.api.Exception.HaveNotToken;
import com.example.vesselcheck.web.api.Exception.KaKaoAuthError;
import com.example.vesselcheck.web.api.dto.*;
import com.example.vesselcheck.web.api.v1.KakaoLogInConst;
import com.example.vesselcheck.web.config.IsToken;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.vesselcheck.web.api.v1.KakaoLogInConst.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientApiV2Controller {
    private final ClientService clientService;
    /**
     * 토큰 받기
     */
    @PostMapping("/v2/get_token")
    public ReturnTokenResponse returnToken(@RequestBody ReturnTokenRequest returnTokenRequest){
        ReturnTokenResponse returnTokenResponse = getToken(returnTokenRequest.getCode());
        ResponseKakaoClient responseKakaoClient = getKaKaoInfo(returnTokenResponse.getAccess_token());
        if(clientService.clientInfoBy(responseKakaoClient.getId()) == null) returnTokenResponse.setIs_our_client(false);
        else returnTokenResponse.setIs_our_client(true);
        returnTokenResponse.setName(responseKakaoClient.getKakao_account().getProfile().getNickname());
        returnTokenResponse.setEmail(responseKakaoClient.getKakao_account().getEmail());
        log.info("returnTokenResponse [{}]",returnTokenResponse);
        return returnTokenResponse;
    }
    /**
     * 회원 가입
     */
    @PostMapping("/v2/join")
    @IsToken
    public PostResult client_infoSave(@Valid @RequestBody ClientSaveRequest clientSaveRequest, HttpServletRequest req){
        log.info("clientSaveRequest {}",clientSaveRequest);
        clientService.clientRegister(clientSaveRequest.getName(),clientSaveRequest.getBelongs(),
                clientSaveRequest.getEmail(),clientSaveRequest.getDuty(),clientSaveRequest.getClientType(),
                getKaKaoInfo(req.getHeader("Authorization")).getId());

        return new PostResult("OK");
    }
    /**
     * 클라이언트 정보
     */
    @GetMapping("/v2/client")
    @IsToken
    public ClientInfo client_info(HttpServletRequest req){
        return clientService.clientInfoBy(getKaKaoInfo(req.getHeader("Authorization")).getId());

    }

}
