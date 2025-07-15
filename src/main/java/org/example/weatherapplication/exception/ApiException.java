package org.example.weatherapplication.exception;

public class ApiException extends RuntimeException {
    public ApiException(String msg){ super(msg); }
}