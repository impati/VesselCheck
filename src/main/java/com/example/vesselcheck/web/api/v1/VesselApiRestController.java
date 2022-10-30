package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.service.Dto.VesselInfo;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import com.example.vesselcheck.domain.service.VesselService;
import com.example.vesselcheck.web.api.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VesselApiRestController {
    private final VesselService vesselService;
    private final ClientRepository clientRepository;

    /**
     * 선박 조회
     */
    @GetMapping("/v1/vessel/list")
    public VesselListResponse vesselList(@ModelAttribute VesselListRequest vesselListRequest, HttpServletRequest req){
        log.info("VesselListRequest = [{}]",vesselListRequest);
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        VesselListResponse resp = new VesselListResponse();
        resp.setVesselInfoList(vesselService.vesselInfoList(client.getId(),new VesselSearchCond(vesselListRequest.getImo(),vesselListRequest.getVessel_name(),vesselListRequest.getVessel_type())));
        return resp;
    }


    /**
     * 선박 추가
     */
    @PostMapping("/v1/vessel/add")
    public void vesselAdd(@RequestBody VesselAddRequest vesselAddRequest,HttpServletRequest req){
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        vesselService.addVesselOfClient(vesselAddRequest.getImo(),client.getId());
    }

    /**
     * 선박 등록
     * - 검사관만 이용할 수 있음 TODO
     */
    @PostMapping("/v1/vessel/register")
    public void vesselRegister(@RequestBody VesselRegisterRequest vesselRegisterRequest){
        vesselService.vesselRegister(vesselRegisterRequest.getIMO(),vesselRegisterRequest.getVessel_name(),vesselRegisterRequest.getVessel_type());
    }


    /**
     * 사용자 선박 상세
     * - 사용자 선박이라면 어떠한 사용자건 상관 없음
     * - 검사관이라도 해당 선박을 소유하고 있지 않다면 접근할 수 없음 ->TODO
     */
    @GetMapping("/v1/vessel/{vesselIMO}")
    public VesselInfo vesselPage(@PathVariable String vesselIMO,HttpServletRequest req){
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        return vesselService.vesselInfo(client.getId(),vesselIMO);
    }


    /**
     * MY 선박 Page
     */
    @GetMapping("/v1/client/vessels")
    public VesselListResponse clientVesselList(HttpServletRequest req) {
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        VesselListResponse resp = new VesselListResponse();
        vesselService.searchVessel(client.getId())
                .stream()
                .map(ele-> new VesselInfo(ele.getId(),ele.getIMO(),ele.getVesselName(),ele.getVesselType(),true))
                .forEach(ele->resp.getVesselInfoList().add(ele));
        return resp;
    }

}
