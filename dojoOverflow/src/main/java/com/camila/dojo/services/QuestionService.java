package com.camila.dojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camila.dojo.models.QuestionsModel;
import com.camila.dojo.repositories.QuestionsRepo;

@Service
public class QuestionService {
//INYECCION
	@Autowired
	private final QuestionsRepo questionsRepo;
	@Autowired
	public QuestionService(QuestionsRepo qRepo) {
		this.questionsRepo=qRepo;
	}

//CREAR UNA PREGUNTA
	public QuestionsModel crearPregunta(QuestionsModel question) {
		return questionsRepo.save(question);
	}
	
	//MOSTRAR TODAS LAS PREGUNTAS
	public List<QuestionsModel> allPreguntas() {
		return (List<QuestionsModel>) questionsRepo.findAll();
	}
	
	//MOSTRAR PREGUNTA POR ID
	public QuestionsModel encontrarQuestionPorId(Long id) {
		return questionsRepo.findById(id).orElse(null);
	}



}
