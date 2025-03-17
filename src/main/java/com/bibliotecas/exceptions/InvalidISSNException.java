package com.bibliotecas.exceptions;

public class InvalidISSNException extends RuntimeException {
    public InvalidISSNException(String message) {
        super(message);
    }
}
