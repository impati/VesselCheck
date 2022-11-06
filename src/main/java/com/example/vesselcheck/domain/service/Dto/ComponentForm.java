package com.example.vesselcheck.domain.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class ComponentForm{
    @NotNull
    private String blockName ;
    @NotEmpty
    private List<String> componentName;//enum?
    @NotEmpty
    private List<String> sequenceNumber;
    @NotEmpty
    private List<MultipartFile> imageUploadName;
}
