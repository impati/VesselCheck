package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.domain.Repository.BlockRepository;
import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.service.ComponentService;
import com.example.vesselcheck.domain.service.Dto.BlockSearchCond;
import com.example.vesselcheck.web.api.dto.BlockRegisterRequest;
import com.example.vesselcheck.web.api.dto.BlockSearchResponse;
import com.example.vesselcheck.web.api.dto.PostResult;
import com.example.vesselcheck.web.api.v1.KakaoLogInConst;
import com.example.vesselcheck.web.config.IsToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ComponentV2Controller{

    private final BlockRepository blockRepository;
    private final ComponentService componentService;
    private final VesselRepository vesselRepository;
    private final ClientRepository clientRepository;

    @PostMapping("/v2/block/register")
    @IsToken
    public PostResult blockRegister(@Valid @ModelAttribute BlockRegisterRequest blockRegisterRequest , HttpServletRequest req){
        Vessel vessel = vesselRepository.findByIMO(blockRegisterRequest.getImo()).orElse(null);
        Client client = clientRepository.findByKakaoId(KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId()).orElse(null);
        componentService.registerBlock(vessel.getId(),client.getId(),blockRegisterRequest.getBlockName(),blockRegisterRequest.getWorkingStep());
        return new PostResult("OK");
    }

    @GetMapping("/v2/block/list")
    @IsToken
    public BlockSearchResponse blockSearch(@ModelAttribute BlockSearchCond blockSearchCond){
        BlockSearchResponse resp = new BlockSearchResponse();
        resp.setBlockInfoList(componentService.searchBlock(blockSearchCond));
        return resp;
    }


}
