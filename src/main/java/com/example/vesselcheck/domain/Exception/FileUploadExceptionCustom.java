package com.example.vesselcheck.domain.Exception;

public class FileUploadExceptionCustom extends RuntimeException{
    public FileUploadExceptionCustom(String message) {
        super(message);
    }
}
