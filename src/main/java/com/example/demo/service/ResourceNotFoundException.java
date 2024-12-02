package com.example.demo.service;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String s) {
        super(s);
    }
}
