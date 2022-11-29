package com.bowling.exception;

public class DataFileNotFoundException extends RuntimeException {
    public DataFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
