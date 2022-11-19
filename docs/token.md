
# 토큰 및 회원 유무 받기

```
  post /v2/get_token
  Content-type : application/json
```


- Request

변수명| 타입  |required|설명
  ---|-----|---|---|
  code|String|true|인가 코드

  - Response

변수명| 타입  |required|설명
---|-----|---|---|
  - token_type|String|true|토큰 타입:bearer
  - access_token|String|true|사용자 액세스 토큰 값
  - expires_in|Integer|true|액세스 토큰과 ID 토큰의 만료 시간(초)
  - refresh_token|String|true|사용자 리프레시 토큰 값
  - refresh_token_expires_in|Integer|true|리프레시 토큰 만료 시간(초)
  - is_our_client|Boolean|true|리프레시 토큰 만료 시간(초)
  - name|String|true|카카오 닉네임
  - email|String|true|카카오 이메일
