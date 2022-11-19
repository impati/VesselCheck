# 에러

- [401] 카카오 인증 에러 : 카카오 로그인 인증 실패
- [400] 필수 값 누락 에러 : require 값이 오지 않음 ;  message [ ”필드명:이유” ,,,]
- [401] 카카오 토큰 에러 : 카카오 토큰 만료 및 토큰 없음 , 로그인한 상태에 접근할 수 없음
- [400] 입력 값에 대한 정보가 존재하지 않음 에러 : request 에 대한 정보가 없음


- 에러 응답 (json)

  변수명| 타입            |required|설명
  ---|---------------|---|---|
  code| Integer       |true|코드
  status| String       |true|httpstatus
  message| List\<String> |true|에러 이유를 설명