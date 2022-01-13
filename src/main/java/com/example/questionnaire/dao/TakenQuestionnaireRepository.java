package com.example.questionnaire.dao;

import com.example.questionnaire.domain.TakenQuestionnaireEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TakenQuestionnaireRepository extends CrudRepository<TakenQuestionnaireEntity, Long> {
    Optional<TakenQuestionnaireEntity> findByUserIdAndQuestionnaireId(Long userId, Long questionnaireId);
}
