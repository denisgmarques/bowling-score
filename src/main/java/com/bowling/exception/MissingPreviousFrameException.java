package com.bowling.exception;

public class MissingPreviousFrameException extends RuntimeException {
    public MissingPreviousFrameException(String errorMessage) {
        super(errorMessage);
    }
}
