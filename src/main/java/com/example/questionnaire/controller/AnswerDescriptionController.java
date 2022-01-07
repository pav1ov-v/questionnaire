package com.example.questionnaire.controller;

import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.service.AnswerDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/description")
public class AnswerDescriptionController {
    private final AnswerDescriptionService answerDescriptionService;

    @Autowired
    public AnswerDescriptionController(AnswerDescriptionService answerDescriptionService) {
        this.answerDescriptionService = answerDescriptionService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDescription(
            @RequestBody AnswerDescriptionEntity description,
            @RequestParam Long questionId
    ) {
        try {
            answerDescriptionService.createDescription(description, questionId);
            return ResponseEntity.ok("Ответ добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
