# 부품 재업로드

```
    Patch /v2/component/re-upload
    Authorization : ${access_token}
    Content-type : multipart/form-data
```

- Request

변수명| 타입            |required|설명
  ---|---------------|---|---|
componentId| Long          |true|부품 아이디
imageUploadName| MultipartFile |true|부품 이미지