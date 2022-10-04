package com.example.vesselcheck.web.controller;


import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.ClientInfo;
import com.example.vesselcheck.domain.service.ClientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ClientService clientService;
    @GetMapping("/")
    public String home(@CookieValue(name ="client",required = false) Long clientId , Model model){
        if(clientId == null) return "home";
        ClientInfo clientInfo = clientService.clientInfo(clientId);
        if(clientInfo.getName().equals("wnsduds1") || clientInfo.getName().equals("impati")) {
            model.addAttribute("client",clientInfo);
            model.addAttribute("clientId",clientId);
            return "loginHome";
        }
        return "home";
    }

    /**
     * 로그 아웃.
     */
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "client");
        return "redirect:/";
    }

    /**
     * 회원가입 페이지
     */
    @GetMapping("/client/add")
    public String clientAddPage(Model model){
        model.addAttribute("clientTypes", ClientType.values());
        model.addAttribute("clientLoginDto",new ClientLoginDto());
        return "client/addClientForm";
    }

    /**
     * 회원 가입
     */
    @PostMapping("/client/add")
    public String clientAdd(@ModelAttribute ClientLoginDto clientLoginDto, BindingResult result){
        log.info("clientLoginDto {}",clientLoginDto);
        if(clientLoginDto.getLoginId().equals("sa") && clientLoginDto.getPassword().equals("sa")){ // 회원가입 코드
            clientService.clientRegister(clientLoginDto.getName(),
                    clientLoginDto.getBelongs(),
                    clientLoginDto.getEmail(), clientLoginDto.getClientType());
            return "redirect:/";
        }
        result.reject(null,"아이디와 비밀번호가 맞지않습니다");
        return "redirect:/client/add";
    }

    /**
     * 사용자 페이지
     */
    @GetMapping("/client/{clientId}")
    public String clientPage(@PathVariable Long clientId ,Model model){
        ClientInfo clientInfo = clientService.clientInfo(clientId);
        model.addAttribute("clientInfo",clientInfo);
        log.info("clientInfo {}",clientInfo);
        return "client/clientPage";
    }


    /**
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "client/loginForm";
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm , BindingResult bindingResult,
                        HttpServletResponse response){
        int flag = loginForm.check();
        if(flag != 0 ){
            Cookie idCookie = new Cookie("client",String.valueOf(flag));
            response.addCookie(idCookie);
            return "redirect:/";
        }
        bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        return "client/loginForm";
    }



    @Data
    static class LoginForm{
        @NotEmpty
        private String loginId;
        @NotEmpty
        private String password;
        public int check() {
            if (this.getLoginId().equals("wnsduds1") && this.getPassword().equals("1234")) return 1; // 검사관
            if (this.getLoginId().equals("impati") && this.getPassword().equals("1234")) return 2; //제조업체
            else return 0;
        }
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }




}
