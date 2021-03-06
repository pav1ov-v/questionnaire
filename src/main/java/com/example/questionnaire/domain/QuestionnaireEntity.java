package com.example.questionnaire.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class QuestionnaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
    private Set<QuestionEntity> questions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
    private Set<TakenQuestionnaireEntity> takenQuestionnaires;

    public QuestionnaireEntity() {
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

    public Set<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionEntity> questions) {
        this.questions = questions;
    }

    public Set<TakenQuestionnaireEntity> getTakenQuestionnaires() {
        return takenQuestionnaires;
    }

    public void setTakenQuestionnaires(Set<TakenQuestionnaireEntity> takenQuestionnaires) {
        this.takenQuestionnaires = takenQuestionnaires;
    }
}
