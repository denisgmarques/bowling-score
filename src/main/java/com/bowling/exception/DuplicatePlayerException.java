package com.bowling.exception;

public class DuplicatePlayerException extends RuntimeException {
    public DuplicatePlayerException(String errorMessage) {
        super(errorMessage);
    }
}
