package com.example.vesselcheck.web.api.dto;

import lombok.Data;

@Data
public class VesselAddRequest extends JustRequest{
    private String IMO;
}
