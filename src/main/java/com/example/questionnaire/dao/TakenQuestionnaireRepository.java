package com.example.questionnaire.dao;

import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TakenQuestionnaireRepository extends CrudRepository<TakenQuestionnaireEntity, Long> {
    Optional<TakenQuestionnaireEntity> findByUserId(Long userId);

    Optional<TakenQuestionnaireEntity> findByQuestionnaireId(Long questionnaireId);

    Optional<TakenQuestionnaireEntity> findByUserIdAndQuestionnaireId(Long userId, Long questionnaireId);
}
