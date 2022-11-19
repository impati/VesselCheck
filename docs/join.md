
# 회원 가입

```
  post /v2/join
  Content-type : application/json
  Authorization : ${access_token}
```


- Request

변수명| 타입  |required|설명
  ---|-----|---|---|
name|String|true|이름
email|String|true|이메일
belongs|String|true|소속
duty|String|true|직책
clientType|ClientType|true|INSPECTOR,MANUFURER


