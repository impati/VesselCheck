package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.web.api.Exception.HaveNotToken;
import com.example.vesselcheck.web.api.Exception.KaKaoAuthError;
import com.example.vesselcheck.web.api.dto.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {ClientApiV2Controller.class})
public class ExControllerAdvice {
    @ExceptionHandler(KaKaoAuthError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult kakaoAuth(KaKaoAuthError e){
        e.printStackTrace();
        return new ErrorResult("Bad Request",e.getMessage());
    }
    @ExceptionHandler(HaveNotToken.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult haveNotToken(HaveNotToken e){
        e.printStackTrace();
        return new ErrorResult("Bad Request",e.getMessage());
    }
}
