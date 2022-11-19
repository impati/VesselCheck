# 부품 업로드

```
    POST /v2/component/register
    Authorization : ${access_token}
    Content-type : multipart/form-data
```

- Request

변수명| 타입                   |required|설명
  ---|----------------------|---|---|
blockName| String               |true|블럭 명
componentName| List\<String>        |true|부품 명
sequenceNumber| List\<String>        |true|부품 일련 번호
imageUploadName| List\<MultipartFile> |true|부품 이미지