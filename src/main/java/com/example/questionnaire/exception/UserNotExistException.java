package com.example.questionnaire.exception;

public class UserNotExistException extends Exception{
    public UserNotExistException(String message) {
        super(message);
    }
}
