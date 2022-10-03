package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.ClientVessel;
import com.example.vesselcheck.domain.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientVesselRepository extends JpaRepository<ClientVessel,Long> {
    @Query(" select cv from ClientVessel cv " +
            " join cv.client c " +
            " join fetch cv.vessel v " +
            " where cv.client.id = :clientId ")
    List<ClientVessel> findVesselByClient(Long clientId);
}
