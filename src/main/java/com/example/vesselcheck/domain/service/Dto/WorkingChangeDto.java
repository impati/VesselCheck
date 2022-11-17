package com.example.vesselcheck.domain.service.Dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class WorkingChangeDto {
    @NotNull
    private Long componentId;
}
