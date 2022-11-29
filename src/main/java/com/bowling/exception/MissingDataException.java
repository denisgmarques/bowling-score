package com.bowling.exception;

public class MissingDataException extends RuntimeException {
    public MissingDataException(String errorMessage) {
        super(errorMessage);
    }
}
