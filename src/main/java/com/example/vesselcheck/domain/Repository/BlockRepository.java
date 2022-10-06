package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,Long> {

    Block findBlockByBlockName(String blockName);
}
