# 부품 작업 상태 변환

```
    Patch /v2/component/working-status
    Authorization : ${access_token}
    Content-type : application/json
```

- Request

변수명| 타입            |required|설명
  ---|---------------|---|---|
componentId| Long          |true|부품 아이디