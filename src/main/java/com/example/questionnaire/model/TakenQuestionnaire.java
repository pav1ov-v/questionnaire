package com.example.questionnaire.model;

import com.example.questionnaire.domain.TakenQuestionnaireEntity;

import java.time.LocalDateTime;

public class TakenQuestionnaire {
    private Long id;
    private LocalDateTime started;
    private LocalDateTime ended;
    private QuestionnaireFastView questionnaire;

    public static TakenQuestionnaire toModel(TakenQuestionnaireEntity entity) {
        TakenQuestionnaire model = new TakenQuestionnaire();

        model.setId(entity.getId());
        model.setStarted(entity.getStarted());
        model.setEnded(entity.getEnded());
        model.setQuestionnaire(QuestionnaireFastView.toModel(entity.getQuestionnaire()));

        return model;
    }

    public TakenQuestionnaire() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    public void setEnded(LocalDateTime ended) {
        this.ended = ended;
    }

    public QuestionnaireFastView getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireFastView questionnaire) {
        this.questionnaire = questionnaire;
    }
}
