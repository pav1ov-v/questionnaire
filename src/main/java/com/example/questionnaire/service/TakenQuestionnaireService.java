package com.example.questionnaire.service;

import com.example.questionnaire.dao.TakenQuestionnaireRepository;
import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import com.example.questionnaire.exception.QuestionnaireAlreadyTakenByUserException;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
import com.example.questionnaire.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TakenQuestionnaireService {
    private final TakenQuestionnaireRepository takenQuestionnaireRepository;
    private final QuestionnaireService questionnaireService;
    private final UserService userService;

    @Autowired
    public TakenQuestionnaireService(TakenQuestionnaireRepository takenQuestionnaireRepository, QuestionnaireService questionnaireService, UserService userService) {
        this.takenQuestionnaireRepository = takenQuestionnaireRepository;
        this.questionnaireService = questionnaireService;
        this.userService = userService;
    }

    public void getTakeQuestionnaireByUser(Long userId, Long questionnaireId)
            throws QuestionnaireAlreadyTakenByUserException, QuestionnaireNotExistException, UserNotExistException {

        if (
                takenQuestionnaireRepository.findByUserId(userId).isEmpty() &&
                        takenQuestionnaireRepository.findByQuestionnaireId(questionnaireId).isEmpty()) {
            TakenQuestionnaireEntity takenQuestionnaire = new TakenQuestionnaireEntity();

            takenQuestionnaire.setUser(userService.getUserById(userId));
            takenQuestionnaire.setQuestionnaire(questionnaireService.getQuestionnaireById(questionnaireId));
            takenQuestionnaire.setStarted(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));

            takenQuestionnaireRepository.save(takenQuestionnaire);
        } else {
            throw new QuestionnaireAlreadyTakenByUserException("Опрос уже взять данным пользователем");
        }
    }
}
