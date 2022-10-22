package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.service.Dto.ComponentInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComponentInfoList {
    List<ComponentInfo> componentInfoList = new ArrayList<>();
}
