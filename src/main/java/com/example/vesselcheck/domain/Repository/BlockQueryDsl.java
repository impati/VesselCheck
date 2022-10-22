package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Block;
import com.example.vesselcheck.domain.service.Dto.BlockSearchCond;

import java.util.List;

public interface BlockQueryDsl {
    List<Block> searchBlock(BlockSearchCond blockSearchCond);
}
