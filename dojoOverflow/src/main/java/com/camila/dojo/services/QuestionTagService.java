package com.camila.dojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camila.dojo.models.QuestionTagModel;
import com.camila.dojo.repositories.QuestionTagRepo;

@Service
public class QuestionTagService {


	//INYECCION DE DEPENDENCIAS
	@Autowired
	private final QuestionTagRepo questionTagRepo;
	@Autowired
	public QuestionTagService(QuestionTagRepo questionTagRepo) {
		this.questionTagRepo = questionTagRepo;
	}
	
	//CREAR QUESTIONTAG
	public QuestionTagModel guardarQuestionTag(QuestionTagModel questionTag) {
		return questionTagRepo.save(questionTag);
	}

    //OBTENER TODAS LAS IDEAS
    public List<QuestionTagModel> obtenerTodosLosQuestionTag() {
        return (List<QuestionTagModel>) questionTagRepo.findAll();
    }
}
