package com.example.questionnaire.dao;

import com.example.questionnaire.domain.AnswerDescriptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDescriptionRepository extends CrudRepository<AnswerDescriptionEntity, Long> {
    void deleteAllByQuestionId(Long questionId);
}
