package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Component;
import com.example.vesselcheck.domain.entity.FaultType;
import com.example.vesselcheck.domain.entity.QComponent;
import com.example.vesselcheck.domain.entity.WorkingStatus;
import com.example.vesselcheck.domain.service.ComponentSearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.example.vesselcheck.domain.entity.QComponent.component;

/**
 * TODO : 블락 정보 검색 추가해야함.
 */
@Slf4j
@RequiredArgsConstructor
public class ComponentQueryDslImpl implements ComponentQueryDsl{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Component> searchComponent(ComponentSearchCond componentSearchCond) {
        return jpaQueryFactory.selectFrom(component)
                .where(faultTypeEq(componentSearchCond.getFaultType()),
                        componentNameEq(componentSearchCond.getComponentName()),
                        sequenceNumberEq(componentSearchCond.getSequenceNumber()),
                                workingStatusEq(componentSearchCond.getWorkingStatus()))
                .fetch();
    }

    private BooleanExpression faultTypeEq(FaultType faultType){
        return faultType == null ? null : component.faultType.eq(faultType);
    }
    private BooleanExpression componentNameEq(String name){
        return name == null ? null : component.componentName.eq(name);
    }
    private BooleanExpression sequenceNumberEq(String number){
        return number == null ? null : component.sequenceNumber.eq(number);
    }
    private BooleanExpression workingStatusEq(WorkingStatus status){
        return status == null ? null : component.workingStatus.eq(status);
    }


}
