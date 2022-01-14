package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.exception.QuestionNotExistException;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
import com.example.questionnaire.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionnaireService questionnaireService;
    private final AnswerDescriptionService answerDescriptionService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionnaireService questionnaireService, @Lazy AnswerDescriptionService answerDescriptionService) {
        this.questionRepository = questionRepository;
        this.questionnaireService = questionnaireService;
        this.answerDescriptionService = answerDescriptionService;
    }

    public void createQuestion(QuestionEntity question, Long questionnaireId) throws QuestionnaireNotExistException {
        if (questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            QuestionnaireEntity questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);

            question.setQuestionnaire(questionnaire);

            questionRepository.save(question);
        } else {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }

    }

    public QuestionEntity getQuestionById(Long id) throws QuestionNotExistException {
        QuestionEntity question;
        if (questionRepository.findById(id).isPresent()) {
            question = questionRepository.findById(id).get();
        } else {
            throw new QuestionNotExistException("Вопроса с таким id не существует");
        }
        return question;
    }

    public Question getQuestionModelById(Long id) throws QuestionNotExistException {
        return Question.toModel(getQuestionById(id));
    }

    public boolean questionByIdIsPresent(Long id) {
        return questionRepository.findById(id).isPresent();
    }

    public void updateQuestionById(QuestionEntity newQuestion, Long id) throws QuestionNotExistException {
        if (questionRepository.findById(id).isPresent()) {
            answerDescriptionService.deleteAllAnswerDescriptionByQuestionId(id);

            QuestionEntity oldQuestion = questionRepository.findById(id).get();

            oldQuestion.setQuestionContent(newQuestion.getQuestionContent());
            oldQuestion.setTypeOfAnswer(newQuestion.getTypeOfAnswer());

            questionRepository.save(oldQuestion);
        } else {
            throw new QuestionNotExistException("Вопроса с таким id не сущестует");
        }
    }

    @Transactional
    public void deleteQuestionById(Long id) throws QuestionNotExistException {
        if (questionRepository.findById(id).isPresent()) {
            questionRepository.deleteById(id);
        } else {
            throw new QuestionNotExistException("Вопроса с таким id не существует");
        }
    }
}
