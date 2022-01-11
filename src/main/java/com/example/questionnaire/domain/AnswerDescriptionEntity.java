package com.example.questionnaire.domain;

import javax.persistence.*;

@Entity
public class AnswerDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerDescription;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public AnswerDescriptionEntity() {
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}
