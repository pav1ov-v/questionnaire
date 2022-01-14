package com.example.questionnaire.controller;

import com.example.questionnaire.exception.*;
import com.example.questionnaire.service.TakenQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> submitQuestionnaireById(@RequestParam Long id) {
        try {
            takenQuestionnaireService.submitQuestionnaireById(id);
            return ResponseEntity.ok("Прохождение опроса завершено");
        } catch (QuestionnaireAlreadySubmittedByUserException | TakenQuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getBy")
    public ResponseEntity<?> getTakenQuestionnaireModelById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(takenQuestionnaireService.getTakenQuestionnaireModelById(id));
        } catch (TakenQuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getAllBy")
    public ResponseEntity<?> getTakenQuestionnairesByUserId(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(takenQuestionnaireService.getTakenQuestionnairesByUserId(userId));
        } catch (UserNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
