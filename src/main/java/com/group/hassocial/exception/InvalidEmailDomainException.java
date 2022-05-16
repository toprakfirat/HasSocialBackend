package com.group.hassocial.exception;

public class InvalidEmailDomainException extends Exception{

    public InvalidEmailDomainException(String format, String errorMessage) {
        super(errorMessage);
    }

}
