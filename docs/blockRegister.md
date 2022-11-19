# 블럭 저장

```
  POST /v2/block/register
  Authorization : ${access_token}
  Content-type : application/json}
```

- Response

변수명| 타입  |required|설명
  ---|-----|---|---|
imo|String|true|선박 IMO
blockName|String|true|블럭 명
workingStep|WorkingStep|true|PieceAssembly("소조립"),SubBlockAssembly("중조립"),BlockAssembly("대조립”),INSTALL("선행 의장")