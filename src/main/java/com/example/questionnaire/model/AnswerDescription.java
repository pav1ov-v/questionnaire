package com.example.questionnaire.model;

import com.example.questionnaire.domain.AnswerDescriptionEntity;

public class AnswerDescription {
    private String description;

    public static AnswerDescription toModel(AnswerDescriptionEntity entity){
        AnswerDescription model = new AnswerDescription();
        model.setDescription(entity.getDescription());
        return model;
    }

    public AnswerDescription() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
