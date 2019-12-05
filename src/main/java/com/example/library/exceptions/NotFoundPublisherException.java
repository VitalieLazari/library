package com.example.library.exceptions;

public class NotFoundPublisherException extends RuntimeException {

    public NotFoundPublisherException(){
        super();
    }

    public NotFoundPublisherException(String message) {
        super(message);
    }
}
