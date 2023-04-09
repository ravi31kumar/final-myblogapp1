package com.myblog1.myblog1.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public BlogApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
