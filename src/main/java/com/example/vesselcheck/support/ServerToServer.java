package com.example.vesselcheck.support;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
public class ServerToServer {
    @Data
    static class TestResponse{
        private String auth;
        private String name;
    }

    //1.get방식 요청
    public void hello(){


        //URI를 빌드한다
        URI uri = UriComponentsBuilder
                .fromUriString("http://43.200.231.35")
                .path("/test/{id}")
                .encode(Charset.defaultCharset())
                .build()
                .expand(1)
                .toUri();
        log.info("uri {}",uri);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<TestResponse> result = restTemplate.getForEntity(uri,TestResponse.class);

        log.info("TestResponse {}",result);
    }



}
