package com.bowling.exception;

public class ExtraScoreException extends RuntimeException {
    public ExtraScoreException(String errorMessage) {
        super(errorMessage);
    }
}
