package com.example.questionnaire.model;

import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.QuestionnaireEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Questionnaire {
    private Long id;
    private String title;
    private String description;
    private List<Question> questions;

    public static Questionnaire toModel(QuestionnaireEntity entity) {
        Questionnaire model = new Questionnaire();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());

        Set<QuestionEntity> questionEntities = entity.getQuestions();
        List<Question> questions = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntities) {
            questions.add(Question.toModel(questionEntity));
        }

        model.setQuestions(questions);

        return model;
    }

    public static Questionnaire toModelFastView(QuestionnaireEntity entity) {
        Questionnaire model = new Questionnaire();

        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());

        return model;
    }

    public Questionnaire() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
