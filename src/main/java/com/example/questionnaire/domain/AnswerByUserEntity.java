package com.example.questionnaire.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class AnswerByUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String answer;

    @OneToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public AnswerByUserEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}
