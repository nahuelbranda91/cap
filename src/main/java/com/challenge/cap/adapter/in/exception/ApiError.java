package com.challenge.cap.adapter.in.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String details;

    public ApiError(HttpStatus status, String details) {
        this.status = status;
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}