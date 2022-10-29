package com.example.vesselcheck.domain.entity;

public enum WorkingStep {
    PieceAssembly("소조립"), // 절단/벤딩된 부재들(주로 소형)을 몇개씩 용접하여 다음 단계에서 사용할 조립품을 만드는 작업
    SubBlockAssembly("중조립"), //플레이트와 프레임을 조립하고, 절단/벤딩된 부재 및 소조립 부재들을 부착하여 블록의 한 면을 이루는 패널을 만드는 작업
    BlockAssembly("대조립"), //  중조립품, 소조립품, 절단/벤딩된 부재등을 종합하여 선체 공간의 한 구역을 조립하는 작업
    INSTALL("선행 의장"); //
    private String title;

    WorkingStep(String title){
        this.title = title;
    }

}
