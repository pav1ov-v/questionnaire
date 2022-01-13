package com.example.questionnaire.model;

import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import com.example.questionnaire.domain.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TakenQuestionnairesByUser {
    private Long userId;
    private String username;
    private List<TakenQuestionnaire> takenQuestionnaires;

    public static TakenQuestionnairesByUser toModel(UserEntity entity) {
        TakenQuestionnairesByUser model = new TakenQuestionnairesByUser();

        model.setUserId(entity.getId());
        model.setUsername(entity.getUsername());

        Set<TakenQuestionnaireEntity> takenQuestionnaireEntities = entity.getTakenQuestionnaires();
        List<TakenQuestionnaire> takenQuestionnaires = new ArrayList<>();

        for (TakenQuestionnaireEntity takenQuestionnaireEntity : takenQuestionnaireEntities) {
            takenQuestionnaires.add(TakenQuestionnaire.toModel(takenQuestionnaireEntity));
        }

        model.setTakenQuestionnaires(takenQuestionnaires);

        return model;
    }

    public TakenQuestionnairesByUser() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TakenQuestionnaire> getTakenQuestionnaires() {
        return takenQuestionnaires;
    }

    public void setTakenQuestionnaires(List<TakenQuestionnaire> takenQuestionnaires) {
        this.takenQuestionnaires = takenQuestionnaires;
    }
}
