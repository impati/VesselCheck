package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.service.Dto.VesselInfo;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import com.example.vesselcheck.domain.service.VesselService;
import com.example.vesselcheck.web.api.dto.*;
import com.example.vesselcheck.web.api.v1.KakaoLogInConst;
import com.example.vesselcheck.web.config.IsToken;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VesselApiV2Controller {

    private final VesselService vesselService;
    private final ClientRepository clientRepository;

    /**
     * 선박 조회
     */
    @GetMapping("/v2/vessel/list")
    @IsToken
    public VesselListResponse vesselList(@ModelAttribute VesselListRequest vesselListRequest,
                                         HttpServletRequest req){
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        VesselListResponse resp = new VesselListResponse();
        resp.setVesselInfoList(vesselService.vesselInfoList(client.getId(),
                new VesselSearchCond(vesselListRequest.getImo(),vesselListRequest.getVessel_name(),
                        vesselListRequest.getVessel_type(),vesselListRequest.getTon())));
        return resp;
    }


    /**
     * 선박 추가
     */
    @PostMapping("/v2/vessel/add")
    @IsToken
    public PostResult vesselAdd(@Valid @RequestBody VesselAddRequest vesselAddRequest, HttpServletRequest req){
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        vesselService.addVesselOfClient(vesselAddRequest.getImo(),client.getId());
        return new PostResult("Ok");
    }

    /**
     * 선박 등록
     */
    @PostMapping("/v2/vessel/register")
    @IsToken
    public PostResult vesselRegister(@Valid @RequestBody VesselRegisterRequest vesselRegisterRequest , HttpServletRequest req){
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        vesselService.vesselRegister(kakaoId, vesselRegisterRequest.getIMO(),vesselRegisterRequest.getVessel_name(),
                vesselRegisterRequest.getVessel_type(),vesselRegisterRequest.getTotalTon(),
                vesselRegisterRequest.getStartDate(),vesselRegisterRequest.getEndDate());
        return new PostResult("Ok");
    }
    /**
     * 내 선박 조회
     */
    @GetMapping("/v2/client-vessel")
    @IsToken
    public VesselListResponse clientVesselList(HttpServletRequest req){
        Long kakaoId = KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId();
        VesselListResponse resp = new VesselListResponse();
        vesselService.searchVesselV2(kakaoId)
                .stream()
                .map(ele-> new VesselInfo(ele.getId(),ele.getIMO(),ele.getVesselName(),ele.getVesselType(),ele.getTotalTon(),ele.getStartDate(),ele.getEndDate(),true))
                .forEach(ele->resp.getVesselInfoList().add(ele));
        return resp;
    }


    /**
     * 사용자 선박 상세
     * - 로그인한 사용자라면 접근할 수 있다.
     */
    @GetMapping("/v2/vessel/{imo}")
    @IsToken
    public VesselInfo vesselInfo(@PathVariable String imo , HttpServletRequest req){
        return vesselService.vesselInfoV2(imo);
    }







}
