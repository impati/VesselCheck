package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block,Long> ,BlockQueryDsl{
    Block findBlockByBlockName(String blockName);

    @Query("select b from Block b" +
            " join b.vessel v " +
            " where v.IMO = :IMO and b.blockName like concat('%',:name,'%')")
    List<Block> findBlock(@Param("IMO")String IMO , @Param("name") String name);




}
