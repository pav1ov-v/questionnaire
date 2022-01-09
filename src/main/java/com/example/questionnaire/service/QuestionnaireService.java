package com.example.questionnaire.service;

import com.example.questionnaire.dao.QuestionnaireRepository;
import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
import com.example.questionnaire.model.Questionnaire;
import com.example.questionnaire.model.QuestionnaireFastView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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

    public Questionnaire getQuestionnaireById(Long id) throws QuestionnaireNotExistException {
        QuestionnaireEntity questionnaire;
        if (questionnaireRepository.findById(id).isPresent()) {
            questionnaire = questionnaireRepository.findById(id).get();
        } else {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }
        return Questionnaire.toModel(questionnaire);
    }

    public Set<QuestionnaireFastView> getAllQuestionnairesInfo() {
        Set<QuestionnaireEntity> questionnaireEntities = new HashSet<>();
        questionnaireRepository.findAll().forEach(questionnaireEntities::add);

        Set<QuestionnaireFastView> questionnaires = new HashSet<>();

        for (QuestionnaireEntity questionnaireEntity : questionnaireEntities) {
            questionnaires.add(QuestionnaireFastView.toModel(questionnaireEntity));
        }

        return questionnaires;
    }

    public void updateQuestionnaireById(QuestionnaireEntity newQuestionnaire, Long id) throws QuestionnaireNotExistException {
        if (questionnaireRepository.findById(id).isPresent()) {
            QuestionnaireEntity oldQuestionnaire = questionnaireRepository.findById(id).get();

            oldQuestionnaire.setTitle(newQuestionnaire.getTitle());
            oldQuestionnaire.setDescription(newQuestionnaire.getDescription());
            questionnaireRepository.save(oldQuestionnaire);
        } else {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }
    }

    @Transactional
    public void deleteQuestionnaireById(Long id) throws QuestionnaireNotExistException {
        if (questionnaireRepository.findById(id).isPresent()) {
            questionnaireRepository.deleteById(id);
        } else {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }
    }
}
