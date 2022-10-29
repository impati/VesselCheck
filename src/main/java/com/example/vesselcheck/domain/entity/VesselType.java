package com.example.vesselcheck.domain.entity;
/**
 * https://ship-sea-story-65.tistory.com/2
 */
public enum VesselType {
    General("일반 화물선"),
    Container("컨테이너 선"),
    CrudeOil("원유 운반선"),
    Product("정제유운반선"),
    Chemical("화공품운반선"),
    Gas("가스 운반선"),
    DryBulk("건살 물선"),
    Ore("광석 전용선"),
    Refrigerated("냉동선");
    private String title;
    VesselType(String title){
        this.title = title;
    }

}
