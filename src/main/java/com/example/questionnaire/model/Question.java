package com.example.questionnaire.model;

import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.TypeOfAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Question {
    private Long id;
    private String questionContent;
    private TypeOfAnswer typeOfAnswer;
    private List<AnswerDescription> answerDescriptions;

    public static Question toModel(QuestionEntity entity){
        Question model = new Question();

        model.setId(entity.getId());
        model.setQuestionContent(entity.getQuestionContent());
        model.setTypeOfAnswer(entity.getTypeOfAnswer());

        Set<AnswerDescriptionEntity> answerDescriptionEntities = entity.getAnswerDescriptions();
        List<AnswerDescription> answerDescriptions = new ArrayList<>();

        for(AnswerDescriptionEntity answerDescriptionEntity : answerDescriptionEntities){
            answerDescriptions.add(AnswerDescription.toModel(answerDescriptionEntity));
        }

        model.setAnswerDescriptions(answerDescriptions);

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

    public TypeOfAnswer getTypeOfAnswer() {
        return typeOfAnswer;
    }

    public void setTypeOfAnswer(TypeOfAnswer typeOfAnswer) {
        this.typeOfAnswer = typeOfAnswer;
    }

    public List<AnswerDescription> getAnswerDescriptions() {
        return answerDescriptions;
    }

    public void setAnswerDescriptions(List<AnswerDescription> answerDescriptions) {
        this.answerDescriptions = answerDescriptions;
    }
}
