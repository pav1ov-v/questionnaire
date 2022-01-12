package com.example.questionnaire.controller;

import com.example.questionnaire.exception.*;
import com.example.questionnaire.service.TakenQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/takenQuestionnaire")
public class TakenQuestionnaireController {
    private final TakenQuestionnaireService takenQuestionnaireService;

    @Autowired
    public TakenQuestionnaireController(TakenQuestionnaireService takenQuestionnaireService) {
        this.takenQuestionnaireService = takenQuestionnaireService;
    }

    @PostMapping("/takeBy")
    public ResponseEntity<String> takeQuestionnaireByUser(
            @RequestParam Long userId,
            @RequestParam Long questionnaireId
    ) {
        try {
            takenQuestionnaireService.takeQuestionnaireByUser(userId, questionnaireId);
            return ResponseEntity.ok("Прохождение опроса начато");
        } catch (UserNotExistException | QuestionnaireNotExistException |
                QuestionnaireAlreadyTakenByUserException | UserAndQuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/submitBy")
    public ResponseEntity<String> submitQuestionnaireByUser(
            @RequestParam Long userId,
            @RequestParam Long questionnaireId
    ) {
        try {
            takenQuestionnaireService.submitQuestionnaireByUser(userId, questionnaireId);
            return ResponseEntity.ok("Прохождение опроса завершено");
        } catch (QuestionnaireNotExistException | UserAndQuestionnaireNotExistException | QuestionnaireAlreadySubmittedByUserException |
                UserNotExistException | QuestionnaireNotTakenByUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
