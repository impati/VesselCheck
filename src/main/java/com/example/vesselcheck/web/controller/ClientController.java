package com.example.vesselcheck.web.controller;

import com.example.vesselcheck.domain.service.ClientInfo;
import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.domain.service.VesselInfo;
import com.example.vesselcheck.domain.service.VesselService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final VesselService vesselService;
    /**
     * 사용자 페이지
     */
    @GetMapping("/{clientId}")
    public String clientPage(@PathVariable Long clientId , Model model){
        ClientInfo clientInfo = clientService.clientInfo(clientId);
        model.addAttribute("clientInfo",clientInfo);
        model.addAttribute("vessels",
                vesselService.searchVessel(clientId).stream()
                        .map(v->new VesselInfo(v.getIMO(),v.getVesselName(),v.getVesselType())).collect(Collectors.toList()));
        log.info("clientInfo {}",clientInfo);
        return "client/clientPage";
    }






}
