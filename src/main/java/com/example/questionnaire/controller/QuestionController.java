package com.example.questionnaire.controller;

import com.example.questionnaire.dao.QuestionRepository;
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
    private final QuestionRepository questionRepository;

    @Autowired
    QuestionController(QuestionService questionService, QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/addQWTA")
    public ResponseEntity<String> addQuestionWithTextAnswer(@RequestBody QuestionEntity question) {
        try {
            questionService.createQuestionWithTextAnswer(question);
            return ResponseEntity.ok("Вопрос добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/addQWRA")
    public ResponseEntity<String> addQuestionWithRadioAnswer(@RequestBody QuestionEntity question) {
        try {
            questionService.createQuestionWithRadioAnswer(question);
            return ResponseEntity.ok("Вопрос добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getQuestionById")
    public Question getQuestionById(Long id) {
        QuestionEntity question = new QuestionEntity();
        if (questionRepository.findById(id).isPresent()) {
            question = questionRepository.findById(id).get();
        }
        return Question.toModel(question);
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
