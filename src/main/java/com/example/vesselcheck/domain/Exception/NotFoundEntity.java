package com.example.vesselcheck.domain.Exception;

public class NotFoundEntity extends RuntimeException{
    public NotFoundEntity(String message) {
        super(message);
    }
}
