package com.example.vesselcheck.domain.Exception;

public class NoAuthorizationAboutVessel extends RuntimeException{
    public NoAuthorizationAboutVessel(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthorizationAboutVessel(String message) {
        super(message);
    }
}
