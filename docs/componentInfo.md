# 부품 상세

```
    GET /v2/component/${componentId}
    Authorization : ${access_token}
```


- Response

Key| 타입                   | required |설명
---|----------------------|----------|---|
componentInfoList| List\<componentInfo> | true     |부품 조회 리스트

- ComponentInfo

Key| 타입            | required |설명
---|---------------|----------|---|
componentName| String        | true     |부품 명
sequenceNumber| String        | true    |부품 일련 번호
workingStatus| WorkingStatus | true    |WorkingStart("재작업 시작"),WorkingIng("작업 중"),WorkingComplete("작업 완료"),InspectionComplete("검사 완료");
blockName| String        | true    |블럭 이름
uploadImageName| String        | true    | 이미지 업로드 명
storeImageUrl| String        | true    | 저장되어 있는 이미지  URI
componentId| Long          | true    | 부품 아이디
blockName| String     | true    | 블럭 이름
imo| String     | true    | 선박 imo
faultType| FaultType     | false    | FaultType 참고



    FaultType:
    GOOD(-1,"정상"),
    FAULT_TYPE_201(0,"가공 불량"),
    FAULT_TYPE_202(1,"단차"),
    FAULT_TYPE_203(2,"덕트 손상"),
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
    FAULT_TYPE_402(12,"파이프 손상");