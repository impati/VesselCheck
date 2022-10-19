package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import com.example.vesselcheck.domain.service.VesselService;
import com.example.vesselcheck.web.api.dto.VesselListRequest;
import com.example.vesselcheck.web.api.dto.VesselListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public VesselListResponse vesselList(@RequestBody VesselListRequest vesselListRequest){
        Long kakaoId = KakaoLogInConst.getId(vesselListRequest.getAccess_token()).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        VesselListResponse resp = new VesselListResponse();
        resp.setVesselInfoList(vesselService.vesselInfoList(client.getId(),new VesselSearchCond(vesselListRequest.getIMO(),vesselListRequest.getVessel_name(),vesselListRequest.getVessel_type())));
        return resp;
    }


}
