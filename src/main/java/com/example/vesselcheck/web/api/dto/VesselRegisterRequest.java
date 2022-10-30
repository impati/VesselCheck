package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.entity.VesselType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VesselRegisterRequest{
    @NotBlank
    private String IMO;
    @NotBlank
    private String vessel_name;
    @NotNull
    private VesselType vessel_type;
    @NotNull
    private Integer totalTon;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
