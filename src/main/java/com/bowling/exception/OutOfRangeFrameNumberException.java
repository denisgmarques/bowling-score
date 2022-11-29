package com.bowling.exception;

public class OutOfRangeFrameNumberException extends RuntimeException {
    public OutOfRangeFrameNumberException(String errorMessage) {
        super(errorMessage);
    }
}
