package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import com.example.vesselcheck.domain.service.VesselService;
import com.example.vesselcheck.web.api.dto.VesselAddRequest;
import com.example.vesselcheck.web.api.dto.VesselListRequest;
import com.example.vesselcheck.web.api.dto.VesselListResponse;
import com.example.vesselcheck.web.api.dto.VesselRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VesselApiRestController {
    private final VesselService vesselService;
    private final ClientRepository clientRepository;

    /**
     * 선박 조회
     */
    @GetMapping("/v1/vessel/list")
    public VesselListResponse vesselList(@RequestBody VesselListRequest vesselListRequest){
        Long kakaoId = KakaoLogInConst.getId(vesselListRequest.getAccess_token()).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        VesselListResponse resp = new VesselListResponse();
        resp.setVesselInfoList(vesselService.vesselInfoList(client.getId(),new VesselSearchCond(vesselListRequest.getIMO(),vesselListRequest.getVessel_name(),vesselListRequest.getVessel_type())));
        return resp;
    }

    /**
     * 선박 등록
     */
    @PostMapping("/v1/vessel/register")
    public void vesselRegister(@RequestBody VesselRegisterRequest vesselRegisterRequest){
        vesselService.vesselRegister(vesselRegisterRequest.getIMO(),vesselRegisterRequest.getVessel_name(),vesselRegisterRequest.getVessel_type());
    }


    /**
     * 선박 추가
     */
    @PostMapping("/v1/vessel/add")
    public void vesselAdd(@RequestBody VesselAddRequest vesselAddRequest){
        Long kakaoId = KakaoLogInConst.getId(vesselAddRequest.getAccess_token()).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        vesselService.addVesselOfClient(vesselAddRequest.getIMO(),client.getId());
    }

    /**
     * 사용자 선박 상세
     * - 사용자 선박이라면 어떠한 사용자건 상관 없음
     * - 검사관이라도 해당 선박을 소유하고 있지 않다면 접근할 수 없음
     */







}
