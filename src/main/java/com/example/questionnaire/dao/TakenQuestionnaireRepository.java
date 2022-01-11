package com.example.questionnaire.dao;

import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TakenQuestionnaireRepository extends CrudRepository<TakenQuestionnaireEntity, Long> {
    Optional<?> findByUserId(Long userId);

    Optional<?> findByQuestionnaireId(Long questionnaireId);
}
