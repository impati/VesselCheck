package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component,Long> ,ComponentQueryDsl{
}
