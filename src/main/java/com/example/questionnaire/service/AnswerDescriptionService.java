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

    public AnswerDescription createDescription(AnswerDescriptionEntity description, Long questionId) {
        QuestionEntity question;
        if (questionRepository.findById(questionId).isPresent()) {
            question = questionRepository.findById(questionId).get();
            description.setQuestion(question);
        }
        return AnswerDescription.toModel(answerDescriptionRepository.save(description));

    }
}
