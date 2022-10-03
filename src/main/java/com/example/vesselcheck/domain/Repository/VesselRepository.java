package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VesselRepository extends JpaRepository<Vessel,Long> ,VesselQueryDsl{
}
