package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.dao.QuestionnaireRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.exception.QuestionNotExistException;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
import com.example.questionnaire.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final AnswerDescriptionService answerDescriptionService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionnaireRepository questionnaireRepository, AnswerDescriptionService answerDescriptionService) {
        this.questionRepository = questionRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.answerDescriptionService = answerDescriptionService;
    }

    public void createQuestion(QuestionEntity question, Long questionnaireId) throws QuestionnaireNotExistException {
        if (questionnaireRepository.findById(questionnaireId).isPresent()) {
            QuestionnaireEntity questionnaire = questionnaireRepository.findById(questionnaireId).get();

            question.setQuestionnaire(questionnaire);

            questionRepository.save(question);
        } else {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }

    }

    public Question getQuestionById(Long id) throws QuestionNotExistException {
        QuestionEntity question;
        if (questionRepository.findById(id).isPresent()) {
            question = questionRepository.findById(id).get();
        } else {
            throw new QuestionNotExistException("Вопроса с таким id не существует");
        }
        return Question.toModel(question);
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
