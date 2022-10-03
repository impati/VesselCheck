package com.example.vesselcheck.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Manufacturer")
@Getter
public class Manufacturer extends Client{
    private Specialty specialty;
}
