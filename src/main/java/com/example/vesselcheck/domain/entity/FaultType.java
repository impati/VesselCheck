package com.example.vesselcheck.domain.entity;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
@Getter
public enum FaultType {
    GOOD(-1,"정상"),
    FAULT_TYPE_202(0,"단차"),
    FAULT_TYPE_203(1,"덕트 손상"),
    FAULT_TYPE_304(2,"오버랩"), // change
    FAULT_TYPE_401(3,"볼트 체결 불량"),
    FAULT_TYPE_402(4,"파이프 손상"),
    FAULT_TYPE_204(5,"도장 불량"),
    FAULT_TYPE_205(6,"바인딩 불량"),
    FAULT_TYPE_206(7,"보강재 설치 불량"),
    FAULT_TYPE_208(8,"설치 불량"),
    FAULT_TYPE_209(9,"연결 불량"),
    FAULT_TYPE_211(10,"케이블 손상"),
    FAULT_TYPE_212(11,"테이프 불량"),
    FAULT_TYPE_303(12,"언더컷"); // change

    private Integer classId;
    private String title;
    FaultType(Integer i,String title){
        this.classId = i;
        this.title = title;
    }
}
