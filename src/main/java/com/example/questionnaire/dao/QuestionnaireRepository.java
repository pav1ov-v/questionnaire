package com.example.questionnaire.dao;

import com.example.questionnaire.domain.QuestionnaireEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends CrudRepository<QuestionnaireEntity, Long> {
}
