package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.dao.AnswerDescriptionRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.exception.QuestionNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void createAnswerDescription(
            AnswerDescriptionEntity answerDescription, Long questionId
    ) throws QuestionNotExistException {
        if (questionRepository.findById(questionId).isPresent()) {
            QuestionEntity question = questionRepository.findById(questionId).get();

            answerDescription.setQuestion(question);

            answerDescriptionRepository.save(answerDescription);
        } else {
            throw new QuestionNotExistException("Вопроса с таким id не существует");
        }

    }

    @Transactional
    public void deleteAllAnswerDescriptionByQuestionId(Long id) {
        answerDescriptionRepository.deleteAllByQuestionId(id);
    }
}
