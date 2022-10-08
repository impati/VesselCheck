package com.example.vesselcheck.web.controller;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.*;
import com.example.vesselcheck.domain.service.*;
import com.example.vesselcheck.domain.service.Dto.ComponentSearchCond;
import com.example.vesselcheck.domain.service.Dto.VesselInfo;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import com.example.vesselcheck.web.config.SessionConst;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/vessel")
public class VesselController {

    private final VesselService vesselService;
    private final ClientVesselService clientVesselService;
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final ComponentService componentService;
    /**
     * 사용자 선박 페이지
     */
    @GetMapping("/{vesselId}")
    public String vesselPage(@PathVariable Long vesselId,
                             @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId ,
                             @ModelAttribute ComponentSearchCond componentSearchCond,
                             Model model){

        Client client = clientRepository.findById(clientId).orElse(null);
        VesselInfo vesselInfo = vesselService.vesselInfo(vesselId);
        model.addAttribute("vesselInfo",vesselInfo);

        if(client instanceof Manufacturer){

            componentSearchCond.setVesselId(vesselId);
            componentSearchCond.setClientId(clientId);
            componentSearchCond.setWorkingStatus(WorkingStatus.W);
            model.addAttribute("components",componentService.searchComponent(componentSearchCond));
            return "vessel/Manufacturer";
        }
        else{
            return "vessel/Inspector";
        }

    }


    /**
     * 선박 조회
     */
    @GetMapping("/vessels")
    public String searchVessel(@ModelAttribute VesselSearchCond vesselSearchCond ,
                               @SessionAttribute(name = SessionConst.LOGIN_CLIENT) Long clientId ,
                               Model model){
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
