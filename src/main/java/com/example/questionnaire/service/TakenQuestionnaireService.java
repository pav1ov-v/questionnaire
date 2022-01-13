package com.example.questionnaire.service;

import com.example.questionnaire.dao.TakenQuestionnaireRepository;
import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import com.example.questionnaire.exception.*;
import com.example.questionnaire.model.TakenQuestionnaire;
import com.example.questionnaire.model.TakenQuestionnairesByUser;
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

        if (userService.userByIdIsPresent(userId) && questionnaireService.questionnaireByIdIsPresent(questionnaireId) &&
                takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).isEmpty()
        ) {
            TakenQuestionnaireEntity takenQuestionnaire = new TakenQuestionnaireEntity();

            takenQuestionnaire.setUser(userService.getUserById(userId));
            takenQuestionnaire.setQuestionnaire(questionnaireService.getQuestionnaireById(questionnaireId));
            takenQuestionnaire.setStarted(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));

            takenQuestionnaireRepository.save(takenQuestionnaire);

        } else if (takenQuestionnaireRepository.findByUserIdAndQuestionnaireId(userId, questionnaireId).isPresent()) {
            throw new QuestionnaireAlreadyTakenByUserException("Пользователь ранее начинал прохождение данного опроса");
        }
    }

    public void submitQuestionnaireById(Long id) throws TakenQuestionnaireNotExistException, QuestionnaireAlreadySubmittedByUserException {
        TakenQuestionnaireEntity takenQuestionnaire = getTakenQuestionnaireById(id);

        if (takenQuestionnaire.getEnded() == null) {
            takenQuestionnaire.setEnded(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));
            takenQuestionnaireRepository.save(takenQuestionnaire);
        } else {
            throw new QuestionnaireAlreadySubmittedByUserException("Пользователь ранее сдавал данный опрос");
        }
    }

    public TakenQuestionnaireEntity getTakenQuestionnaireById(Long id) throws TakenQuestionnaireNotExistException {
        TakenQuestionnaireEntity takenQuestionnaire = new TakenQuestionnaireEntity();

        if (takenQuestionnaireRepository.findById(id).isPresent()) {
            takenQuestionnaire = takenQuestionnaireRepository.findById(id).get();

        } else if (takenQuestionnaireRepository.findById(id).isEmpty()) {
            throw new TakenQuestionnaireNotExistException("Записи под данным id не существует");
        }
        return takenQuestionnaire;
    }

    public TakenQuestionnaire getTakenQuestionnaireModelById(Long id) throws TakenQuestionnaireNotExistException {
        return TakenQuestionnaire.toModel(getTakenQuestionnaireById(id));
    }

    public void userIdAndQuestionnaireIdIsPresent(Long userId, Long questionnaireId)
            throws UserAndQuestionnaireNotExistException, UserNotExistException, QuestionnaireNotExistException {

        if (!userService.userByIdIsPresent(userId) && !questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            throw new UserAndQuestionnaireNotExistException("Пользователя и опроса с такими id не существует");

        } else if (!userService.userByIdIsPresent(userId)) {
            throw new UserNotExistException("Пользователя с таким id не существует");

        } else if (!questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            throw new QuestionnaireNotExistException("Опроса с таким id не существует");
        }
    }

    public TakenQuestionnairesByUser getTakenQuestionnairesByUserId(Long id) throws UserNotExistException {
        return TakenQuestionnairesByUser.toModel(userService.getUserById(id));
    }
}
