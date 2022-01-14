package com.example.questionnaire.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionContent;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private QuestionnaireEntity questionnaire;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Set<AnswerDescriptionEntity> answerDescriptions;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "question")
    private AnswerByUserEntity answerByUser;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeOfAnswer typeOfAnswer;

    public QuestionEntity() {
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

    public Set<AnswerDescriptionEntity> getAnswerDescriptions() {
        return answerDescriptions;
    }

    public void setAnswerDescriptions(Set<AnswerDescriptionEntity> answerDescriptions) {
        this.answerDescriptions = answerDescriptions;
    }

    public QuestionnaireEntity getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireEntity questionnaire) {
        this.questionnaire = questionnaire;
    }
}
