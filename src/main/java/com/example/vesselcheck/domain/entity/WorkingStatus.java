package com.example.vesselcheck.domain.entity;

public enum WorkingStatus {
    WorkingStart("재작업 시작"),
    WorkingIng("작업 중"),
    WorkingComplete("작업 완료"),
    InspectionComplete("검사 완료");
    private String title;
    WorkingStatus(String title){
        this.title = title;
    }
}
