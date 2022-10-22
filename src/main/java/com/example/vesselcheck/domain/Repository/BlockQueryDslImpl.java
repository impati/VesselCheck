package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Block;
import com.example.vesselcheck.domain.entity.QBlock;
import com.example.vesselcheck.domain.entity.QVessel;
import com.example.vesselcheck.domain.service.Dto.BlockSearchCond;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.vesselcheck.domain.entity.QBlock.block;
import static com.example.vesselcheck.domain.entity.QVessel.vessel;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BlockQueryDslImpl implements BlockQueryDsl{
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Block> searchBlock(BlockSearchCond blockSearchCond) {
        BooleanBuilder builder = new BooleanBuilder();
        if(blockSearchCond.getBlock_name() != null){
            builder.and(block.blockName.contains(blockSearchCond.getBlock_name()));
        }
        if(blockSearchCond.getWorking_step() != null){
            builder.and(block.workingStep.eq(blockSearchCond.getWorking_step()));
        }
        if(StringUtils.hasText(blockSearchCond.getImo())){
            builder.and(vessel.IMO.eq(blockSearchCond.getImo()));
        }
        return queryFactory.selectFrom(block)
                .join(block.vessel, vessel)
                .where(builder)
                .fetch();
    }
}
