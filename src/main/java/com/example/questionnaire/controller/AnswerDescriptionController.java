package com.example.questionnaire.controller;

import com.example.questionnaire.domain.AnswerDescriptionEntity;
import com.example.questionnaire.exception.QuestionNotExistException;
import com.example.questionnaire.service.AnswerDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answerDescription")
public class AnswerDescriptionController {
    private final AnswerDescriptionService answerDescriptionService;

    @Autowired
    public AnswerDescriptionController(AnswerDescriptionService answerDescriptionService) {
        this.answerDescriptionService = answerDescriptionService;
    }

    @PostMapping("/addTo")
    public ResponseEntity<String> addAnswerDescription(
            @RequestBody AnswerDescriptionEntity answerDescription,
            @RequestParam Long questionId
    ) {
        try {
            answerDescriptionService.createAnswerDescription(answerDescription, questionId);
            return ResponseEntity.ok("Ответ добавлен к вопросу");
        } catch (QuestionNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
