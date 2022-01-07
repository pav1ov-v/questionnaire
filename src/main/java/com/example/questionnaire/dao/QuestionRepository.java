package com.example.questionnaire.dao;

import com.example.questionnaire.domain.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
}
