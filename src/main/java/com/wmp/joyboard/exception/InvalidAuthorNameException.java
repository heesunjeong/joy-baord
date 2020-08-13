package com.wmp.joyboard.exception;

public class InvalidAuthorNameException extends RuntimeException {
    public InvalidAuthorNameException() {
        super();
    }

    public InvalidAuthorNameException(String message) {
        super(message);
    }
}
