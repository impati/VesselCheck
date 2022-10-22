package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.service.Dto.BlockInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlockSearchResponse {
    List<BlockInfo> blockInfoList = new ArrayList<>();
}
