package com.example.questionnaire.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String questionContent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    Set<AnswerDescriptionEntity> descriptions;

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

    public Set<AnswerDescriptionEntity> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<AnswerDescriptionEntity> description) {
        this.descriptions = description;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionContent='" + questionContent + '\'' +
                ", typeOfAnswer=" + typeOfAnswer +
                '}';
    }
}
