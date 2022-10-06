package com.example.vesselcheck.domain.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class ComponentForm{
    private String blockName ;
    private List<String> componentName;//enum?
    private List<String> sequenceNumber;
    private List<MultipartFile> imageUploadName;
}
