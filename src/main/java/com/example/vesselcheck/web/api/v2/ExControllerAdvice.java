package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.domain.Exception.NotFoundEntity;
import com.example.vesselcheck.web.api.Exception.HaveNotToken;
import com.example.vesselcheck.web.api.Exception.KaKaoAuthError;
import com.example.vesselcheck.web.api.dto.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(assignableTypes = {ClientApiV2Controller.class,VesselApiV2Controller.class,ComponentV2Controller.class})
public class ExControllerAdvice {
    @ExceptionHandler(KaKaoAuthError.class)
    public ErrorResult kakaoAuth(KaKaoAuthError e){
        e.printStackTrace();
        return new ErrorResult(401,HttpStatus.BAD_REQUEST, List.of(e.getMessage()));
    }
    @ExceptionHandler(HaveNotToken.class)
    public ErrorResult haveNotToken(HaveNotToken e){
        e.printStackTrace();
        return new ErrorResult(401,HttpStatus.BAD_REQUEST, List.of(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult argumentNotValidation(MethodArgumentNotValidException e){
        List<String> errorMessages = e.getFieldErrors().stream().map(ele->ele.getField() + ":" + ele.getDefaultMessage()).collect(Collectors.toList());
        return new ErrorResult(400,HttpStatus.BAD_REQUEST,errorMessages);
    }


    /**
     * NotFoundEntity 공통 처리를 어떻게 해볼 수 있을까
     */
    @ExceptionHandler(NotFoundEntity.class)
    public ErrorResult notFoundEntity(NotFoundEntity e){
        return new ErrorResult(400,HttpStatus.BAD_REQUEST,List.of(e.getMessage()));
    }
}
