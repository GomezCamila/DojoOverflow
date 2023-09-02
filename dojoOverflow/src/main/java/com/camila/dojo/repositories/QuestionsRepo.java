package com.camila.dojo.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.camila.dojo.models.QuestionsModel;

@Repository
public interface QuestionsRepo extends CrudRepository<QuestionsModel, Long>{

}
