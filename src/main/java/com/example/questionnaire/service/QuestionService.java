package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionRepository;
import com.example.questionnaire.dao.QuestionnaireRepository;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.domain.TypeOfAnswer;
import com.example.questionnaire.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionnaireRepository questionnaireRepository) {
        this.questionRepository = questionRepository;
        this.questionnaireRepository = questionnaireRepository;
    }

    public void createQuestionWithTextAnswer(QuestionEntity question) {
        question.setTypeOfAnswer(TypeOfAnswer.TEXT);
        questionRepository.save(question);
    }

    public void createQuestionWithRadioAnswer(QuestionEntity question, Long questionnaireId) {
        if (questionnaireRepository.findById(questionnaireId).isPresent()) {
            QuestionnaireEntity questionnaire = questionnaireRepository.findById(questionnaireId).get();
            question.setQuestionnaire(questionnaire);
            question.setTypeOfAnswer(TypeOfAnswer.RADIO);
            questionRepository.save(question);
        }
    }

    public void createQuestionWithCheckboxAnswer(QuestionEntity question) {
        question.setTypeOfAnswer(TypeOfAnswer.CHECKBOX);
        questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        QuestionEntity question = new QuestionEntity();
        if (questionRepository.findById(id).isPresent()) {
            question = questionRepository.findById(id).get();
        }
        return Question.toModel(question);
    }
}
