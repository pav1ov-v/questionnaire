package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.dao.AnswerDescriptionRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.model.AnswerDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerDescriptionService {
    private final AnswerDescriptionRepository answerDescriptionRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerDescriptionService(
            AnswerDescriptionRepository answerDescriptionRepository,
            QuestionRepository questionRepository
    ) {
        this.answerDescriptionRepository = answerDescriptionRepository;
        this.questionRepository = questionRepository;
    }

    public void createAnswerDescription(AnswerDescriptionEntity answerDescription, Long questionId) {
        if (questionRepository.findById(questionId).isPresent()) {
            QuestionEntity question = questionRepository.findById(questionId).get();
            answerDescription.setQuestion(question);
            answerDescriptionRepository.save(answerDescription);
        }

    }
}
