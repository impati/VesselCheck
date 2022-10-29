package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.FaultType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ComponentServiceTest {


    private final static String hostName = "http://localhost:8080/images/";
    private final static String imageServerHostName = "http://localhost:9090/";

    private void decision(String imageName){

        String image_path = hostName + imageName;

        System.out.println("imagePath = " + imageName);

        URI uri = UriComponentsBuilder.fromUriString(imageServerHostName +"v1/decision")
                .encode()
                .build()
                .toUri();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ComponentService.componentImageResponse> result =
                new RestTemplate().exchange(new RequestEntity<>(new ComponentService.componentImageRequest(image_path), HttpMethod.POST, uri), ComponentService.componentImageResponse.class);

        System.out.println("result = " + result.getBody());

    }


    @Test
    @DisplayName("모델 서버와 통신")
    public void connectTest() throws Exception{

        String imageName = "56427164-2f2d-4684-a46f-60d39aeb08af.jpg";
        decision(imageName);

    }




    @Test
    @DisplayName("enumClassId")
    public void classIdTest() throws Exception{
        Integer classId = 2;
        FaultType faultType = Arrays.stream(FaultType.values()).
                filter(f->f.getClassId().equals(classId)).findFirst().get();

        Assertions.assertThat(faultType).isEqualTo(FaultType.FAULT_TYPE_304);



    }


}