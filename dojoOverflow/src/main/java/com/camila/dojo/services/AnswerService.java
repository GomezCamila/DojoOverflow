package com.camila.dojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camila.dojo.models.AnswerModel;
import com.camila.dojo.repositories.AnswerRepo;


@Service
public class AnswerService {

//INYECCIONES
	@Autowired
	private AnswerRepo answerRepo;
	public AnswerService(AnswerRepo aRepo) {
		this.answerRepo=aRepo;
	}
	
	//CREAR RESPUESTA
	public AnswerModel crearRespuesta(AnswerModel answer) {
		return answerRepo.save(answer);
	}
//VINCULAR RESPUESTA A PREGUNTA
	
	public List<AnswerModel> respuestaPregunta(Long questionId){
		return answerRepo.findAllByQuestionId(questionId);
	}


}
