package com.example.questionnaire.controller;

import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.exception.QuestionnaireAlreadyTakenByUserException;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
import com.example.questionnaire.exception.UserNotExistException;
import com.example.questionnaire.service.QuestionnaireService;
import com.example.questionnaire.service.TakenQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestionnaire(@RequestBody QuestionnaireEntity questionnaire) {
        try {
            questionnaireService.createQuestionnaire(questionnaire);
            return ResponseEntity.ok(String.format("Опрос добавлен под id = %d", questionnaire.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getBy")
    public ResponseEntity<?> getQuestionnaireModelById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(questionnaireService.getQuestionnaireModelById(id));
        } catch (QuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuestionnairesInfo() {
        try {
            return ResponseEntity.ok(questionnaireService.getAllQuestionnairesInfo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/updateBy")
    public ResponseEntity<String> updateQuestionnaireById(
            @RequestBody QuestionnaireEntity questionnaire,
            @RequestParam Long id
    ) {
        try {
            questionnaireService.updateQuestionnaireById(questionnaire, id);
            return ResponseEntity.ok("Опрос обновлен");
        } catch (QuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/deleteBy")
    public ResponseEntity<String> deleteQuestionnaireById(@RequestParam Long id) {
        try {
            questionnaireService.deleteQuestionnaireById(id);
            return ResponseEntity.ok("Опрос удален");
        } catch (QuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
