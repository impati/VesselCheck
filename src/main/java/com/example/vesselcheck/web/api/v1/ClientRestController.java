package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.web.api.dto.JustRequest;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientRestController {
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @GetMapping("/client_info")
    public String clientInfo(@RequestBody JustRequest justRequest){
        ResponseKakaoClient kakaoClient = KakaoLogInConst.getId(justRequest.getAccess_token());
        return "";
    }
}
