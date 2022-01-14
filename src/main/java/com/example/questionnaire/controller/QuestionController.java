package com.example.questionnaire.controller;

import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.exception.QuestionNotExistException;
import com.example.questionnaire.exception.QuestionnaireNotExistException;
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
            return ResponseEntity.ok(String.format("Вопрос добавлен под id = %d", question.getId()));
        } catch (QuestionnaireNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getBy")
    public ResponseEntity<?> getQuestionById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(questionService.getQuestionModelById(id));
        } catch (QuestionNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/updateBy")
    public ResponseEntity<String> updateQuestionById(
            @RequestBody QuestionEntity question,
            @RequestParam Long id
    ) {
        try {
            questionService.updateQuestionById(question, id);
            return ResponseEntity.ok("Вопрос обновлен, все приложенные к нему ответы удалены");
        } catch (QuestionNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/deleteBy")
    public ResponseEntity<String> deleteQuestionById(@RequestParam Long id) {
        try {
            questionService.deleteQuestionById(id);
            return ResponseEntity.ok("Вопрос удален");
        } catch (QuestionNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
