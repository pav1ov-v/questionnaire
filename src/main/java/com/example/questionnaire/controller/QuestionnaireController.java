package com.example.questionnaire.controller;

import com.example.questionnaire.domain.QuestionnaireEntity;
import com.example.questionnaire.model.Questionnaire;
import com.example.questionnaire.service.QuestionnaireService;
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
            return ResponseEntity.ok("Опрос добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getQuestionnaireById")
    public Questionnaire getQuestionnaireById(@RequestParam Long id) {
        return questionnaireService.getQuestionnaireById(id);
    }
}
