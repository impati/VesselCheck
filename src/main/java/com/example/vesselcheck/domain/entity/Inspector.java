package com.example.vesselcheck.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Inspector extends Client {
    private RankPosition rankPosition;
}
