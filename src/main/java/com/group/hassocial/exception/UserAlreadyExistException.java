package com.group.hassocial.exception;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() {
        super();
    }


    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }


    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
