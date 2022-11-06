package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.*;
import com.example.vesselcheck.domain.service.Dto.ComponentSearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.vesselcheck.domain.entity.QBlock.block;
import static com.example.vesselcheck.domain.entity.QComponents.components;


/**
 * TODO : 블락 정보 검색 추가해야함.
 */
@Slf4j
@RequiredArgsConstructor
public class ComponentQueryDslImpl implements ComponentQueryDsl{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Components> searchComponent(ComponentSearchCond componentSearchCond) {
        log.info("componentSearchCond [{}]",componentSearchCond);
        return jpaQueryFactory.selectFrom(components)
                .join(components.block, block)
                .where(
                        block.vessel.id.eq(componentSearchCond.getVesselId()),
                        blockNameEq(componentSearchCond.getBlockName()),
                        faultTypeEq(componentSearchCond.getFaultType()),
                        componentNameEq(componentSearchCond.getComponentName()),
                        sequenceNumberEq(componentSearchCond.getSequenceNumber()),
                        workingStatusEq(componentSearchCond.getWorkingStatus()))
                .fetch();
    }
    private BooleanExpression blockNameEq(String blockName){
        return !StringUtils.hasText(blockName) ? null : block.blockName.contains(blockName);
    }
    private BooleanExpression faultTypeEq(FaultType faultType){
        return faultType == null ? null : components.faultType.eq(faultType);
    }
    private BooleanExpression componentNameEq(String name){
        return !StringUtils.hasText(name) ? null : components.componentName.contains(name);
    }
    private BooleanExpression sequenceNumberEq(String number){
        return !StringUtils.hasText(number)? null : components.sequenceNumber.eq(number);
    }
    private BooleanExpression workingStatusEq(WorkingStatus status){
        return status == null ? null : components.workingStatus.eq(status);
    }


}
