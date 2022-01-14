package com.example.questionnaire.service;

import com.example.questionnaire.dao.AnswerDescriptionRepository;
import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.exception.AnswerDescriptionNotExistException;
import com.example.questionnaire.exception.QuestionNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerDescriptionService {
    private final AnswerDescriptionRepository answerDescriptionRepository;
    private final QuestionService questionService;

    @Autowired
    public AnswerDescriptionService(
            AnswerDescriptionRepository answerDescriptionRepository,
            QuestionService questionService
    ) {
        this.answerDescriptionRepository = answerDescriptionRepository;
        this.questionService = questionService;
    }

    public void createAnswerDescription(AnswerDescriptionEntity answerDescription, Long questionId)
            throws QuestionNotExistException {

        if (questionService.questionByIdIsPresent(questionId)) {
            QuestionEntity question = questionService.getQuestionById(questionId);

            answerDescription.setQuestion(question);

            answerDescriptionRepository.save(answerDescription);
        } else {
            throw new QuestionNotExistException("Вопроса с данным id не существует");
        }

    }

    public AnswerDescriptionEntity getAnswerDescriptionById(Long id) throws AnswerDescriptionNotExistException {
        AnswerDescriptionEntity answerDescription;

        if (answerDescriptionRepository.findById(id).isPresent()) {
            answerDescription = answerDescriptionRepository.findById(id).get();
        } else {
            throw new AnswerDescriptionNotExistException("Описания ответа под данным id не существует");
        }
        return answerDescription;
    }

    @Transactional
    public void deleteAllAnswerDescriptionByQuestionId(Long id) {
        answerDescriptionRepository.deleteAllByQuestionId(id);
    }
}
