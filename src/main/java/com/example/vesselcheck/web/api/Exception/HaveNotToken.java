package com.example.vesselcheck.web.api.Exception;

public class HaveNotToken extends RuntimeException{
    public HaveNotToken(String message, Throwable cause) {
        super(message, cause);
    }
}
