package com.camila.dojo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.camila.dojo.models.TagsModel;


public interface TagsRepo extends CrudRepository<TagsModel, Long> {
	TagsModel findBySubject(String subject);
	
	   @Query("SELECT t FROM TagsModel t JOIN t.questions q WHERE q.id = :questionId")
	    List<TagsModel> findTagsByQuestionsId(@Param("questionId") Long questionId);
}
