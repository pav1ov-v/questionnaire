package com.example.questionnaire.exception;


public class QuestionnaireAlreadyTakenByUserException extends Exception {
    public QuestionnaireAlreadyTakenByUserException(String message) {
        super(message);
    }
}
