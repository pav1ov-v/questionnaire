package com.example.questionnaire.dao;

import com.example.questionnaire.domain.AnswerByUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerByUserRepository extends CrudRepository<AnswerByUserEntity, Long> {

}
