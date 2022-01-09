package com.example.questionnaire.domain;

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

    @Enumerated(EnumType.STRING)
    private TypeOfAnswer typeOfAnswer;

    public QuestionEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
