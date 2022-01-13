package com.example.questionnaire.model;

import com.example.questionnaire.domain.QuestionnaireEntity;

public class QuestionnaireFastView {
    private Long id;
    private String title;
    private String description;

    public static QuestionnaireFastView toModel(QuestionnaireEntity entity) {
        QuestionnaireFastView model = new QuestionnaireFastView();

        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());



        return model;
    }

    public QuestionnaireFastView() {

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
}
