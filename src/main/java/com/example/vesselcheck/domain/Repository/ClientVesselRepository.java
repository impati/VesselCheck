package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.ClientVessel;
import com.example.vesselcheck.domain.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientVesselRepository extends JpaRepository<ClientVessel,Long> {
    @Query(" select cv from ClientVessel cv " +
            " join cv.client c " +
            " join fetch cv.vessel v " +
            " where cv.client.id = :clientId ")
    List<ClientVessel> findVesselByClient(@Param("clientId") Long clientId);


    @Query(" select cv from ClientVessel cv " +
            " join cv.client c " +
            " join fetch cv.vessel v " +
            " where c.id =:clientId and v in :vesselList")
    List<ClientVessel> findByVesselListAndClient(@Param("clientId") Long clientId, @Param("vesselList") List<Vessel> vesselList);

    @Query(" select count(cv) from ClientVessel cv " +
            " join cv.client c " +
            " join  cv.vessel v " +
            " where c.id = :clientId and v.id = :vesselId ")
    Long existClientAndVessel(@Param("clientId") Long clientId,@Param("vesselId")Long vesselId);

}
