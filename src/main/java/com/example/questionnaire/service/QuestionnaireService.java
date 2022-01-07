package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionnaireRepository;
import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.model.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public void createQuestionnaire(QuestionnaireEntity questionnaire) {
        questionnaireRepository.save(questionnaire);
    }

    public Questionnaire getQuestionnaireById(Long id) {
        QuestionnaireEntity questionnaire = new QuestionnaireEntity();
        if (questionnaireRepository.findById(id).isPresent()) {
            questionnaire = questionnaireRepository.findById(id).get();
        }
        return Questionnaire.toModel(questionnaire);
    }
}
