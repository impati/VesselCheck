package com.example.vesselcheck.domain.service.Dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingChangeDto {
    @NotNull
    private Long componentId;
}
