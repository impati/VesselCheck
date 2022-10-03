package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Component;
import com.example.vesselcheck.domain.service.ComponentSearchCond;

import java.util.List;

public interface ComponentQueryDsl {
    List<Component> searchComponent(ComponentSearchCond componentSearchCond);
}
