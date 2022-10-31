package com.example.projectyweb;

public class UserAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
