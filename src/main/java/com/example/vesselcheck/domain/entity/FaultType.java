package com.example.vesselcheck.domain.entity;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
@Getter
public enum FaultType {
    GOOD(-1,"정상"),
    FAULT_TYPE_201(0,"가공 불량"),
    FAULT_TYPE_202(1,"단차"),
    FAULT_TYPE_203(2,"덕트 손상"), // change
    FAULT_TYPE_205(3,"바인딩 불량"),
    FAULT_TYPE_206(4,"보강재 설치 불량"),
    FAULT_TYPE_207(5,"보온재 손상"),
    FAULT_TYPE_208(6,"설치 불량"),
    FAULT_TYPE_209(7,"연결 불량"),
    FAULT_TYPE_210(8,"연계 처리 불량"),
    FAULT_TYPE_211(9,"케이블 손상"),
    FAULT_TYPE_212(10,"테이프 불량"),
    FAULT_TYPE_213(11,"테이프 불량"),
    FAULT_TYPE_401(12,"볼트 체결 불량"),
    FAULT_TYPE_402(13,"파이프 손상");

    private Integer classId;
    private String title;
    FaultType(Integer i,String title){
        this.classId = i;
        this.title = title;
    }
}
