package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.entity.WorkingStatus;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

@Data
public class ComponentReForm {
    @NotNull
    private Long componentId;
    private WorkingStatus workingStatus;
    private MultipartFile imageUploadName;
}
