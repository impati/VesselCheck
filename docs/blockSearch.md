# 블럭 조회

```
  GET /v2/block/list?imo=${imo}&block_name=${name}&working_step=${step}
  Authorization : ${access_token}
```

- Request Param

Key| 타입  | required |설명
  ---|-----|----------|---|
imo|String| false    | IMO 번호
blockName|String| false     |블럭 이름
workingStep|WorkingStep| false     |PieceAssembly("소조립"),SubBlockAssembly("중조립"),BlockAssembly("대조립”),INSTALL("선행 의장")

- Response

변수명| 타입                |required|설명
  ---|-------------------|---|---|
blockInfoList| List\<BlockInfo> |true|부품 조회 리스트

- BlockInfo

변수명| 타입                | required |설명
  ---|-------------------|----------|---|
imo|String| true     | IMO 번호
blockName|String| true    |블럭 이름
workingStep|WorkingStep| true    |PieceAssembly("소조립"),SubBlockAssembly("중조립"),BlockAssembly("대조립”),INSTALL("선행 의장")
vesselName|String|true|선명