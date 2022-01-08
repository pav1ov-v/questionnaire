package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.dao.QuestionnaireRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.QuestionnaireEntity;
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

    public void createQuestion(QuestionEntity question, Long questionnaireId) {
        if (questionnaireRepository.findById(questionnaireId).isPresent()) {
            QuestionnaireEntity questionnaire = questionnaireRepository.findById(questionnaireId).get();
            question.setQuestionnaire(questionnaire);
            questionRepository.save(question);
        }

    }

    public Question getQuestionById(Long id) {
        QuestionEntity question = new QuestionEntity();
        if (questionRepository.findById(id).isPresent()) {
            question = questionRepository.findById(id).get();
        }
        return Question.toModel(question);
    }

    public void updateQuestionById(QuestionEntity newQuestion, Long id) {
        if (questionRepository.findById(id).isPresent()) {
            answerDescriptionService.deleteAllAnswerDescriptionByQuestionId(id);
            QuestionEntity oldQuestion = questionRepository.findById(id).get();
            oldQuestion.setQuestionContent(newQuestion.getQuestionContent());
            oldQuestion.setTypeOfAnswer(newQuestion.getTypeOfAnswer());
            questionRepository.save(oldQuestion);
        }
    }

    @Transactional
    public void deleteQuestionById(Long id) {
        if (questionRepository.findById(id).isPresent()) {
            questionRepository.deleteById(id);
        }
    }
}
