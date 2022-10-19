package com.example.vesselcheck.web.controller;


import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.Dto.ClientInfo;
import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.web.config.SessionConst;
import com.example.vesselcheck.web.dto.JoinForm;
import com.example.vesselcheck.web.dto.ResponseToken;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final String clientId = "299b0425b0382132eb1f239f7a0f4ae1";
    private final String redirectId = "http://localhost:8080/client/add";
    private final String clientSecret = "vEX71vGS4QzFBz2BFvdIFzfDEFot1fP3";
    private final String logoutRedirect = "http://localhost:8080/logout";
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_CLIENT ,required = false) Long kakaoId , Model model){
        if(kakaoId == null){
            model.addAttribute("client",clientId);
            model.addAttribute("redirect",redirectId);
            return "home";
        }
        else {
            ClientInfo clientInfo = clientService.clientInfoBy(kakaoId);
            model.addAttribute("client",clientInfo);
            model.addAttribute("clientId",clientInfo.getClientId());
            model.addAttribute("apikey",clientId);
            model.addAttribute("logout",logoutRedirect);
            return "loginHome";
        }
    }
    /**
     * 로그 아웃.
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /**
     * 회원가입 페이지
     */
    @GetMapping("/client/add")
    public String clientAddPage(@RequestParam String code, HttpServletRequest req,Model model){
        log.info("code = [{}]",code);
        // 토큰 -> 사용자 정보 받기
        ResponseKakaoClient result = getId(getToken(code));
        Optional<Client> client = clientRepository.findByKakaoId(result.getId());
        if(client.isPresent()){
            Long kakaoId = client.get().getKakaoId();
            //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
            HttpSession session = req.getSession();
            //세션에 로그인 회원 정보 보관
            session.setAttribute(SessionConst.LOGIN_CLIENT, kakaoId);
            return "redirect:/";
        }
        else{
            model.addAttribute("JoinForm",new JoinForm(result));
            model.addAttribute("clientTypes", ClientType.values());
            return "joinForm";
        }
    }


    @PostMapping("/client/add")
    public String kakoJoin(@RequestParam Long kakaoId , @ModelAttribute JoinForm joinForm,HttpServletRequest req){
        log.info("joinForm = [{}]",joinForm);
        Client client= clientService.clientRegister(joinForm.getName(),joinForm.getBelongs(),joinForm.getEmail(), "",joinForm.getClientType(),joinForm.getKakaoId());
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = req.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_CLIENT, client.getKakaoId());
        log.info("session [{}]",session.getAttribute(SessionConst.LOGIN_CLIENT));
        return "redirect:/";
    }

    private ResponseKakaoClient getId(String token){
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com/v2/user/me")
                .build()
                .toUri();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + token);
        httpHeaders.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST,uri);
        ResponseEntity<ResponseKakaoClient> result = new RestTemplate().exchange(requestEntity, ResponseKakaoClient.class);
        log.info("id result = [{}]",result);
        return result.getBody();
    }

    private String getToken(String code){

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/token")
                .queryParam("grant_type","authorization_code")
                .queryParam("client_id",clientId)
                .queryParam("redirect_uri",redirectId)
                .queryParam("code",code)
                .queryParam("client_secret",clientSecret)
                .build()
                .toUri();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity requestEntity = new RequestEntity(httpHeaders, HttpMethod.POST,uri);
        ResponseEntity<ResponseToken> result = new RestTemplate().exchange(requestEntity, ResponseToken.class);
        log.info("result [{}]",result.getBody());
        return result.getBody().getAccess_token();
    }









}
