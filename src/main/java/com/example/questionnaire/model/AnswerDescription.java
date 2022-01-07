package com.example.questionnaire.model;

import com.example.questionnaire.domain.AnswerDescriptionEntity;

public class AnswerDescription {
    private String answerDescription;

    public static AnswerDescription toModel(AnswerDescriptionEntity entity){
        AnswerDescription model = new AnswerDescription();
        model.setAnswerDescription(entity.getAnswerDescription());
        return model;
    }

    public AnswerDescription() {
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }
}
