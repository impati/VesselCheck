package com.example.vesselcheck.web.controller;


import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.ClientInfo;
import com.example.vesselcheck.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ClientService clientService;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/client/add")
    public String clientAddPage(Model model){
        model.addAttribute("clientTypes", ClientType.values());
        model.addAttribute("clientLoginDto",new ClientLoginDto());
        return "client/addClientForm";
    }
    @PostMapping("/client/add")
    public String clientAdd(@ModelAttribute ClientLoginDto clientLoginDto, BindingResult result){
        log.info("clientLoginDto {}",clientLoginDto);
        if(clientLoginDto.getLoginId().equals("wnsduds1") && clientLoginDto.getPassword().equals("1234")){
            Long clientId = clientService.clientRegister(clientLoginDto.getName(),
                    clientLoginDto.getBelongs(),
                    clientLoginDto.getEmail(), clientLoginDto.getClientType());
            return "redirect:/client/" + clientId;
        }
        result.reject(null,"아이디와 비밀번호가 맞지않습니다");
        return "redirect:/client/add";
    }
    @GetMapping("/client/{clientId}")
    public String clientPage(@PathVariable Long clientId ,Model model){
        ClientInfo clientInfo = clientService.clientInfo(clientId);
        model.addAttribute("clientInfo",clientInfo);
        log.info("clientInfo {}",clientInfo);
        return "client/clientPage";
    }



}
