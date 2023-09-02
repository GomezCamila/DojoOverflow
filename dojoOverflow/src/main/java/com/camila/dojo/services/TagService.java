package com.camila.dojo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.camila.dojo.models.TagsModel;

import com.camila.dojo.repositories.TagsRepo;

@Service
public class TagService {


	//INYECCION DE DEPENDENCIAS
	@Autowired
	private final TagsRepo tagRepo;
	@Autowired
	public TagService(TagsRepo tRepo) {
		this.tagRepo = tRepo;
	}
	
	//CREAR UN TAG 
	public TagsModel crearTag(TagsModel tag) {
		return tagRepo.save(tag);
	}
	
	
	//MOSTRAR PREGUNTA POR ID
	public TagsModel encontrarTagPorId(Long id) {
		return tagRepo.findById(id).orElse(null);
	}

	//BUSCAR TAGS EXISTENTES
	public TagsModel existeTag(String nombreTag) {
        return tagRepo.findBySubject(nombreTag);
    }
	
    public List<TagsModel> obtenerTagsRelacionadosAPregunta(Long idPregunta) {
        return tagRepo.findTagsByQuestionsId(idPregunta);
    }
}
