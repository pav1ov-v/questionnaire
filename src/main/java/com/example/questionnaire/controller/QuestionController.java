package com.example.questionnaire.controller;

import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.model.Question;
import com.example.questionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/addTo")
    public ResponseEntity<String> addQuestion(
            @RequestBody QuestionEntity question,
            @RequestParam Long questionnaireId
    ) {
        try {
            questionService.createQuestion(question, questionnaireId);
            return ResponseEntity.ok("Вопрос добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getBy")
    public Question getQuestionById(@RequestParam Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/updateBy")
    public ResponseEntity<String> updateQuestionById(
            @RequestBody QuestionEntity question,
            @RequestParam Long id
    ) {
        try {
            questionService.updateQuestionById(question, id);
            return ResponseEntity.ok("Вопрос обновлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/deleteBy")
    public ResponseEntity<String> deleteQuestionById(@RequestParam Long id) {
        try {
            questionService.deleteQuestionById(id);
            return ResponseEntity.ok("Вопрос удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping()
    public ResponseEntity<String> getStatus() {
        try {
            return ResponseEntity.ok("Сервер работает");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
