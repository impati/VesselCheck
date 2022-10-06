package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Components;
import com.example.vesselcheck.domain.service.Dto.ComponentSearchCond;

import java.util.List;

public interface ComponentQueryDsl {
    List<Components> searchComponent(ComponentSearchCond componentSearchCond);
}
