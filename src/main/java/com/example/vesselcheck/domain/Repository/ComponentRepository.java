package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Components;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Components,Long> ,ComponentQueryDsl{
}
