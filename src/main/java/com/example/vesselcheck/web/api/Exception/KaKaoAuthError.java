package com.example.vesselcheck.web.api.Exception;

public class KaKaoAuthError extends RuntimeException{
    public KaKaoAuthError(String message, Throwable cause) {
        super(message, cause);
    }
}
