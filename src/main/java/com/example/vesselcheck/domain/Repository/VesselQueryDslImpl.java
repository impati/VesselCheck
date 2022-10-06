package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.entity.VesselType;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.vesselcheck.domain.entity.QVessel.vessel;

/**
 * 선박 검색 조건에 따른 조회
 * 모두 null 일 경우에는 조회 x
 */
@Slf4j
@RequiredArgsConstructor
public class VesselQueryDslImpl implements VesselQueryDsl{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Vessel> searchVessel(VesselSearchCond vesselSearchCond) {
        if(isAllNull(vesselSearchCond))return new ArrayList<>();
        return jpaQueryFactory
                .selectFrom(vessel)
                .where(
                        IMOEq(vesselSearchCond.getIMO()),
                        nameEq(vesselSearchCond.getName()),
                        typeEq(vesselSearchCond.getVesselType())
                        )
                .fetch();
    }
    private boolean isAllNull(VesselSearchCond vesselSearchCond){
        if(vesselSearchCond.getVesselType()== null &&
                !StringUtils.hasText(vesselSearchCond.getIMO()) &&
                !StringUtils.hasText(vesselSearchCond.getName())
        )return true;
        return false;
    }
    private BooleanExpression IMOEq(String IMO){
        return !StringUtils.hasText(IMO) ? null :vessel.IMO.eq(IMO);
    }
    private BooleanExpression nameEq(String name){
        return name == null ? null :vessel.vesselName.contains(name);
    }
    private BooleanExpression typeEq(VesselType vesselType){
        return vesselType == null ? null :vessel.vesselType.eq(vesselType);
    }

}
