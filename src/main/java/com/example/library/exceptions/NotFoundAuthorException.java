package com.example.library.exceptions;

public class NotFoundAuthorException extends RuntimeException {

    public NotFoundAuthorException(){
        super();
    }

    public NotFoundAuthorException(String message) {
        super(message);
    }
}
