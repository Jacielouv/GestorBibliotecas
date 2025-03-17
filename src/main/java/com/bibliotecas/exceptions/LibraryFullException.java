package com.bibliotecas.exceptions;

public class LibraryFullException extends RuntimeException {
    public LibraryFullException(String message) {
        super(message);
    }
}
