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
            throw new QuestionnaireAlreadyTakenByUserException("???????????????????????? ?????????? ?????????????? ?????????????????????? ?????????????? ????????????");
        }
    }

    public void submitQuestionnaireById(Long id) throws TakenQuestionnaireNotExistException, QuestionnaireAlreadySubmittedByUserException {
        TakenQuestionnaireEntity takenQuestionnaire = getTakenQuestionnaireById(id);

        if (takenQuestionnaire.getEnded() == null) {
            takenQuestionnaire.setEnded(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));
            takenQuestionnaireRepository.save(takenQuestionnaire);
        } else {
            throw new QuestionnaireAlreadySubmittedByUserException("???????????????????????? ?????????? ???????????? ???????????? ??????????");
        }
    }

    public TakenQuestionnaireEntity getTakenQuestionnaireById(Long id) throws TakenQuestionnaireNotExistException {
        TakenQuestionnaireEntity takenQuestionnaire = new TakenQuestionnaireEntity();

        if (takenQuestionnaireRepository.findById(id).isPresent()) {
            takenQuestionnaire = takenQuestionnaireRepository.findById(id).get();

        } else if (takenQuestionnaireRepository.findById(id).isEmpty()) {
            throw new TakenQuestionnaireNotExistException("???????????? ?????? ???????????? id ???? ????????????????????");
        }
        return takenQuestionnaire;
    }

    public TakenQuestionnaire getTakenQuestionnaireModelById(Long id) throws TakenQuestionnaireNotExistException {
        return TakenQuestionnaire.toModel(getTakenQuestionnaireById(id));
    }

    public void userIdAndQuestionnaireIdIsPresent(Long userId, Long questionnaireId)
            throws UserAndQuestionnaireNotExistException, UserNotExistException, QuestionnaireNotExistException {

        if (!userService.userByIdIsPresent(userId) && !questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            throw new UserAndQuestionnaireNotExistException("???????????????????????? ?? ???????????? ?? ???????????? id ???? ????????????????????");

        } else if (!userService.userByIdIsPresent(userId)) {
            throw new UserNotExistException("???????????????????????? ?? ?????????? id ???? ????????????????????");

        } else if (!questionnaireService.questionnaireByIdIsPresent(questionnaireId)) {
            throw new QuestionnaireNotExistException("???????????? ?? ?????????? id ???? ????????????????????");
        }
    }

    public TakenQuestionnairesByUser getTakenQuestionnairesByUserId(Long id) throws UserNotExistException {
        return TakenQuestionnairesByUser.toModel(userService.getUserById(id));
    }
}
