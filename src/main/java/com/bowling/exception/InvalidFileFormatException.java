package com.bowling.exception;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(String errorMessage) {
        super(errorMessage);
    }
}
