package com.camila.dojo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.camila.dojo.models.QuestionTagModel;

@Repository
public interface QuestionTagRepo extends CrudRepository<QuestionTagModel, Long> {

}
