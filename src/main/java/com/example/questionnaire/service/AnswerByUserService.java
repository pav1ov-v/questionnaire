package com.example.questionnaire.service;

import com.example.questionnaire.dao.AnswerByUserRepository;
import com.example.questionnaire.domain.AnswerByUserEntity;
import com.example.questionnaire.domain.QuestionEntity;
import com.example.questionnaire.domain.TypeOfAnswer;
import com.example.questionnaire.exception.AnswerDescriptionNotExistException;
import com.example.questionnaire.exception.DigitNotEnteredException;
import com.example.questionnaire.exception.MoreThanOneDigitException;
import com.example.questionnaire.exception.QuestionNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AnswerByUserService {
    private final AnswerByUserRepository answerByUserRepository;
    private final QuestionService questionService;
    private final AnswerDescriptionService answerDescriptionService;

    @Autowired
    public AnswerByUserService(AnswerByUserRepository answerByUserRepository,
                               QuestionService questionService, AnswerDescriptionService answerDescriptionService) {
        this.answerByUserRepository = answerByUserRepository;
        this.questionService = questionService;
        this.answerDescriptionService = answerDescriptionService;
    }

    public void createAnswerByUser(AnswerByUserEntity answerByUser, Long questionId)
            throws QuestionNotExistException, MoreThanOneDigitException, AnswerDescriptionNotExistException, DigitNotEnteredException {

        if (questionService.questionByIdIsPresent(questionId)) {
            QuestionEntity question = questionService.getQuestionById(questionId);

            if (answerByUser.getAnswer() != null && !answerByUser.getAnswer().isEmpty()) {
                answerByUser.setQuestion(question);

                if (question.getTypeOfAnswer().equals(TypeOfAnswer.TEXT)) {
                    answerByUserRepository.save(answerByUser);

                } else if (question.getTypeOfAnswer().equals(TypeOfAnswer.RADIO)) {
                    answerByUserRepository.save(setAnswerForRadioQuestion(answerByUser));

                } else if (question.getTypeOfAnswer().equals(TypeOfAnswer.CHECKBOX)) {
                    answerByUserRepository.save(setAnswerForCheckboxQuestion(answerByUser));
                }
            }
        }
    }

    public AnswerByUserEntity setAnswerForRadioQuestion(AnswerByUserEntity answerByUser) throws AnswerDescriptionNotExistException, MoreThanOneDigitException {
        List<String> nums = Arrays.stream(answerByUser.getAnswer().split("")).toList();

        if (nums.size() == 1) {

            Long answerDescId = Long.parseLong(nums.get(0));
            answerByUser.setAnswer(answerDescriptionService.getAnswerDescriptionById(answerDescId).toString());

            return answerByUser;

        } else {
            throw new MoreThanOneDigitException("Введено больше одного индентификатора ответа");
        }
    }

    public AnswerByUserEntity setAnswerForCheckboxQuestion(AnswerByUserEntity answerByUser) throws AnswerDescriptionNotExistException, DigitNotEnteredException {
        List<String> nums = Arrays.stream(answerByUser.getAnswer().split("")).toList();

        if (nums.size() > 0) {

            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < nums.size(); i++) {
                Long answerDescId = Long.parseLong(nums.get(i));
                String partOfAnswer = answerDescriptionService.getAnswerDescriptionById(answerDescId).toString();
                answer.append(partOfAnswer);
                if (i != nums.size() - 1) {
                    answer.append(" | ");
                }
            }
            answerByUser.setAnswer(answer.toString());

            return answerByUser;

        } else {
            throw new DigitNotEnteredException("Индентификатор ответа не был введен");
        }
    }
}
