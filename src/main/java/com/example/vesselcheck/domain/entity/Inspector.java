package com.example.vesselcheck.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Inspector")
@Getter
public class Inspector extends Client{
    private RankPosition rankPosition;
}
