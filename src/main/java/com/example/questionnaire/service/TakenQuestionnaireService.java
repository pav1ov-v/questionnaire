package com.example.questionnaire.service;

import com.example.questionnaire.dao.TakenQuestionnaireRepository;
import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import com.example.questionnaire.exception.*;
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
    public TakenQuestionnaireService(
            TakenQuestionnaireRepository takenQuestionnaireRepository,
            QuestionnaireService questionnaireService, UserService userService
    ) {
        this.takenQuestionnaireRepository = takenQuestionnaireRepository;
        this.questionnaireService = questionnaireService;
        this.userService = userService;
    }

    public void takeQuestionnaireByUser(Long userId, Long questionnaireId)
            throws QuestionnaireAlreadyTakenByUserException, QuestionnaireNotExistException, UserNotExistException, UserAndQuestionnaireNotExistException {

        userIdAndQuestionnaireIdIsPresent(userId, questionnaireId);

        if (userService.UserByIdIsPresent(userId) && questionnaireService.questionnaireByIdIsPresent(questionnaireId) &&
                takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).isEmpty()
        ) {
            TakenQuestionnaireEntity takenQuestionnaire = new TakenQuestionnaireEntity();

            takenQuestionnaire.setUser(userService.getUserById(userId));
            takenQuestionnaire.setQuestionnaire(questionnaireService.getQuestionnaireById(questionnaireId));
            takenQuestionnaire.setStarted(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));

            takenQuestionnaireRepository.save(takenQuestionnaire);

        } else if (takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).isPresent()) {
            throw new QuestionnaireAlreadyTakenByUserException("Пользователь уже начинал проходить данный опрос");
        }
    }

    public void submitQuestionnaireByUser(Long userId, Long questionnaireId)
            throws QuestionnaireNotExistException, UserAndQuestionnaireNotExistException, UserNotExistException,
            QuestionnaireNotTakenByUserException, QuestionnaireAlreadySubmittedByUserException {

        userIdAndQuestionnaireIdIsPresent(userId, questionnaireId);

        TakenQuestionnaireEntity takenQuestionnaire = getTakenQuestionnaireByUser(userId, questionnaireId);

        if (takenQuestionnaire.getEnded() == null) {
            takenQuestionnaire.setEnded(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));
            takenQuestionnaireRepository.save(takenQuestionnaire);
        } else {
            throw new QuestionnaireAlreadySubmittedByUserException("Пользователь уже сдавал данный опрос");
        }
    }

    public TakenQuestionnaireEntity getTakenQuestionnaireByUser(Long userId, Long questionnaireId)
            throws QuestionnaireNotExistException, UserAndQuestionnaireNotExistException, UserNotExistException, QuestionnaireNotTakenByUserException {

        userIdAndQuestionnaireIdIsPresent(userId, questionnaireId);

        TakenQuestionnaireEntity takenQuestionnaire = new TakenQuestionnaireEntity();

        if (takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).isPresent()) {
            takenQuestionnaire = takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).get();

        } else if (userService.UserByIdIsPresent(userId) && questionnaireService.questionnaireByIdIsPresent(questionnaireId) &&
                takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).isEmpty()
        ) {
            throw new QuestionnaireNotTakenByUserException("Пользователь еще не начинал проходить данный опрос");
        }
        return takenQuestionnaire;
    }

    public void userIdAndQuestionnaireIdIsPresent(Long userId, Long questionnaireId)
            throws UserAndQuestionnaireNotExistException, UserNotExistException, QuestionnaireNotExistException {

        if (!userService.UserByIdIsPresent(userId) && !questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            throw new UserAndQuestionnaireNotExistException("Пользователя и опроса с такими id не существует");

        } else if (!userService.UserByIdIsPresent(userId)) {
            throw new UserNotExistException("Пользователя с таким id не существует");

        } else if (!questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }
    }
}
