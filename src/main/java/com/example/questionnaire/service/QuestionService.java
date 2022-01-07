package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.TypeOfAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void createQuestionWithTextAnswer(QuestionEntity question) {
        question.setTypeOfAnswer(TypeOfAnswer.TEXT);
        questionRepository.save(question);
    }

    public void createQuestionWithRadioAnswer(QuestionEntity question) {
        question.setTypeOfAnswer(TypeOfAnswer.RADIO);
        questionRepository.save(question);
    }
}
