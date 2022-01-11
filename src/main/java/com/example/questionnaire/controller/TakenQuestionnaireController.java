package com.example.questionnaire.controller;

import com.example.questionnaire.exception.QuestionnaireAlreadyTakenByUserException;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
import com.example.questionnaire.exception.UserNotExistException;
import com.example.questionnaire.service.TakenQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/takenQuestionnaire")
public class TakenQuestionnaireController {
    private final TakenQuestionnaireService takenQuestionnaireService;

    @Autowired
    public TakenQuestionnaireController(TakenQuestionnaireService takenQuestionnaireService) {
        this.takenQuestionnaireService = takenQuestionnaireService;
    }

    @PostMapping("/getTakeBy")
    public ResponseEntity<String> getTakeQuestionnaireByUser(
            @RequestParam Long userId,
            @RequestParam Long questionnaireId
    ) {
        try {
            takenQuestionnaireService.getTakeQuestionnaireByUser(userId, questionnaireId);
            return ResponseEntity.ok("Прохождение опроса начато");
        } catch (UserNotExistException | QuestionnaireNotExistException | QuestionnaireAlreadyTakenByUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
