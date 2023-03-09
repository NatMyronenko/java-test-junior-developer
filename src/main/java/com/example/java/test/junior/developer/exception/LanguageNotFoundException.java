package com.example.java.test.junior.developer.exception;

public class LanguageNotFoundException extends RuntimeException {

    public LanguageNotFoundException(String message) {
        super(message);
    }
}