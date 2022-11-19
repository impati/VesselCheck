# 내 선박 조회

```
    GET /v2/client-vessel
    Authorization : ${access_token}
```



- Response

변수명| 타입                |required|설명
  ---|-------------------|---|---|
vesselInfoList| List\<VesselInfo> |true|선박 정보 리스트

- VesselInfo

변수명| 타입                |required|설명
  ---|-------------------|---|---|
imo| String |true|IMO 번호
vesselName| String |true|선명
vesselType| VesselType |true|General("일반 화물선"),Container("컨테이너선"),CrudeOil("원유 운반선"),Ore("광석 전용선"),Refrigerated("냉동선")
ownership| Integer |true|항상 true
ton| String |true|총 톤 수
startDate| LocalDate |true|착공 예정일
endDate| LocalDate |true|착공 예정일
