package com.example.NetBanking.Database.Exceptions;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    public UserNotFoundException(String message) {
        super(message);
    }
}

