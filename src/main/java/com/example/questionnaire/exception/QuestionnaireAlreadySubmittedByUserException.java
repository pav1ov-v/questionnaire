package com.example.questionnaire.exception;

public class QuestionnaireAlreadySubmittedByUserException extends Exception{
    public QuestionnaireAlreadySubmittedByUserException(String message) {
        super(message);
    }
}
