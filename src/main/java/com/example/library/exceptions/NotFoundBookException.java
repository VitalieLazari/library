package com.example.library.exceptions;

public class NotFoundBookException extends RuntimeException {

    public NotFoundBookException(){
        super();
    }

    public NotFoundBookException(String message) {
        super(message);
    }
}
