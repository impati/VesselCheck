package com.example.vesselcheck.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResult {
    private Integer code;
    private HttpStatus status;// bad Request
    private List<String> message = new ArrayList<>();
}
