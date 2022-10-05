package com.example.vesselcheck.web.controller;

import com.example.vesselcheck.domain.Repository.ClientVesselRepository;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.entity.ClientVessel;
import com.example.vesselcheck.domain.entity.VesselType;
import com.example.vesselcheck.domain.service.ClientVesselService;
import com.example.vesselcheck.domain.service.VesselInfo;
import com.example.vesselcheck.domain.service.VesselSearchCond;
import com.example.vesselcheck.domain.service.VesselService;
import com.example.vesselcheck.web.config.SessionConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/vessel")
public class VesselController {

    private final VesselService vesselService;
    private final ClientVesselService clientVesselService;
    /**
     * 선박 조회
     */
    @GetMapping("/vessels")
    public String searchVessel(@ModelAttribute VesselSearchCond vesselSearchCond ,
                               @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId , Model model){
        log.info("vesselSearchCond = [{}]",vesselSearchCond);
        List<VesselInfo> vesselInfos = vesselService.vesselInfoList(clientId, vesselSearchCond);
        log.info("vesselInfos = [{}]",vesselInfos);
        model.addAttribute("vessels",vesselInfos);
        model.addAttribute("vesselTypes", VesselType.values());
        return "vessel/vessels";
    }


    /**
     * 클라이언트의 선박 추가 기능.
     * ->POST 로 바뀌어야함.
     */
    @GetMapping("/add/{vesselId}")
    public String addVessel(@SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId,
                            @PathVariable Long vesselId){
        clientVesselService.clientVesselAdd(clientId,vesselId);
        return "redirect:/vessel/vessels";
    }


    /**
     * 선박 등록 페이지
     *
     */
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("VesselForm",new VesselForm());
        model.addAttribute("vesselTypes",VesselType.values());
        return "vessel/register";
    }

    /**
     * 선박 등록
     */
    @PostMapping("/register")
    public String register(@ModelAttribute VesselForm vesselForm){
        vesselService.vesselRegister(vesselForm.getIMO(),vesselForm.getVesselName(),vesselForm.getVesselType());
        return "redirect:/vessel/vessels";
    }



    @Data
    static class VesselForm{
        private String IMO;
        private String vesselName;
        private VesselType vesselType;
    }



}
