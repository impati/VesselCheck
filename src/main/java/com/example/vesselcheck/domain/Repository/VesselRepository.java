package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VesselRepository extends JpaRepository<Vessel,Long> ,VesselQueryDsl{

    @Query("select v from Vessel v " +
            " where v.IMO = :IMO ")
    Optional<Vessel> findByIMO(@Param("IMO") String IMO);
}
