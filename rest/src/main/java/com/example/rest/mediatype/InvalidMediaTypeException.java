package com.example.rest.mediatype;

public class InvalidMediaTypeException extends IllegalArgumentException {

    private static final long serialVersionUID = 183253650406525749L;

    public InvalidMediaTypeException(final String message) {
        super(message);
    }

}
