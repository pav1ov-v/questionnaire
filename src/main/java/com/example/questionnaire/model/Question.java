package com.example.questionnaire.model;

import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.domain.QuestionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Question {
    private Long id;
    private String questionContent;
    private List<AnswerDescription> descriptions;

    public static Question toModel(QuestionEntity entity){
        Question model = new Question();
        model.setId(entity.getId());
        model.setQuestionContent(entity.getQuestionContent());

        Set<AnswerDescriptionEntity> descriptionEntitySet = entity.getDescriptions();
        List<AnswerDescription> descriptionList = new ArrayList<>();

        for(AnswerDescriptionEntity descriptionEntity : descriptionEntitySet){
            descriptionList.add(AnswerDescription.toModel(descriptionEntity));
        }

        model.setDescriptions(descriptionList);

        return model;
    }

    public Question(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<AnswerDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<AnswerDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
