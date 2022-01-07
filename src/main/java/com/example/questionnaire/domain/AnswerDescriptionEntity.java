package com.example.questionnaire.domain;

import javax.persistence.*;

@Entity
public class AnswerDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}
