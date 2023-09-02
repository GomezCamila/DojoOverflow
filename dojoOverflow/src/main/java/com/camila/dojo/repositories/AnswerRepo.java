package com.camila.dojo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.camila.dojo.models.AnswerModel;

@Repository
public interface AnswerRepo extends CrudRepository<AnswerModel, Long>{

List<AnswerModel> findAllByQuestionId(Long questionId);
}
