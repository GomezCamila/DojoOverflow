package com.camila.dojo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camila.dojo.models.AnswerModel;

import com.camila.dojo.models.QuestionsModel;
import com.camila.dojo.models.TagsModel;
import com.camila.dojo.services.AnswerService;
import com.camila.dojo.services.QuestionService;
import com.camila.dojo.services.QuestionTagService;
import com.camila.dojo.services.TagService;

import jakarta.validation.Valid;

@Controller
public class QuestionsController {

	//INYECCIONES
	@Autowired
	private final AnswerService answerService ;
	@Autowired
	private final QuestionService questionService;
	@Autowired
	private final TagService tagService;
	@SuppressWarnings("unused")
	@Autowired
	private final QuestionTagService questionTagService;
	
	
	public QuestionsController(AnswerService answerService,QuestionService questionService,TagService tagService,QuestionTagService questionTagService ) {
		this.answerService=answerService;
		this.questionService=questionService;
		this.questionTagService=questionTagService;
		this.tagService=tagService;
	}
	
	@GetMapping("/")
	public String index(Model viewModel){
		List<QuestionsModel> listaQuestions= questionService.allPreguntas();
		viewModel.addAttribute("questions",listaQuestions);
		return "dash.jsp";
	}
	//NUEVA PREGUNTA
	@GetMapping("/questions/new")
	public String formNuevaPregunta(@ModelAttribute("question")QuestionsModel questionModel,@ModelAttribute("tags")TagsModel tagsModel) {
		return "newQ.jsp";
	}
	
	 @PostMapping("/questions/new")
	    public String nuevaPregunta(
	    		@Valid @ModelAttribute("question")QuestionsModel tagsQuestion,
	            BindingResult result,
	            @RequestParam("etiquetas") String listaEtiquetas,
	            RedirectAttributes redirectAttributes) {
		//ERRORES
		if(result.hasErrors()){
			return"newQ.jsp";
			
		}
	List<TagsModel> tagsList =new ArrayList<>();
	String [] arregloEtiqueta = listaEtiquetas.split(",");
	
	  if(arregloEtiqueta.length > 3) {
          redirectAttributes.addFlashAttribute("error", "No agregues mas de 3 tags");
          return "redirect:/questions/new";
      
	  }
	  if(listaEtiquetas.isBlank()  || listaEtiquetas.isEmpty()) {
		  redirectAttributes.addFlashAttribute("errores", "Por favor no envies mensajes vacios");
		  return "redirect:/questions/new";
		  
		  
	  }
	  for(String temaEtiquetas : arregloEtiqueta) {
		  String unaEtiqueta=temaEtiquetas.trim().toLowerCase();
		  TagsModel existeEtiqueta= tagService.existeTag(unaEtiqueta);
	 
	//si no existe el tag se crea
	  if(existeEtiqueta==null){
		  TagsModel nuevaEtiqueta = new TagsModel();
		  nuevaEtiqueta.setSubject(unaEtiqueta);
		  tagsList.add(nuevaEtiqueta);
	  }else {
		  tagsList.add(existeEtiqueta);
	  }
	  
	  }
	//GUARDAR LOSTAGS Y PREGUNTA EN BASE DE DATOS
	  for(TagsModel etiqueta: tagsList){
		  tagService.crearTag(etiqueta);
		  
	  }
	  tagsQuestion.setTags(tagsList);
	  questionService.crearPregunta(tagsQuestion);
	
	return "redirect:/";
	 
	 }
	
	@GetMapping("/questions/{idQuestion}")
	public String mostrarQuestion(@ModelAttribute("answer")AnswerModel answerForm,
			@PathVariable("idQuestion")Long id, Model viewModel) {
		//OBTENER PREGUNTA POR ID
		QuestionsModel mostrarQuestion=questionService.encontrarQuestionPorId(id);
		viewModel.addAttribute("questionDetalles", mostrarQuestion);
		
		List<TagsModel> tagsRelacionados = tagService.obtenerTagsRelacionadosAPregunta(id);
		viewModel.addAttribute("tagsRelacionados", tagsRelacionados );
		
		List<AnswerModel> listAnswers = answerService.respuestaPregunta(id);
		viewModel.addAttribute("listaDeAnswers", listAnswers);
		viewModel.addAttribute("questionId", id);
		
		return "show.jsp";
		
	}

	//MOSTRAR ENVIAR RESPUESTA
    @PostMapping("/questions/{idQuestion}/answer")
    public String mostrarAnswer(@Valid @ModelAttribute("answer") AnswerModel answer, 
    		BindingResult result, @PathVariable("idQuestion") Long id, Model viewModel){

        if(result.hasErrors()){
            return  "show.jsp";
        }else{
            QuestionsModel mostrarQuestions = questionService.encontrarQuestionPorId(id);

            AnswerModel newAnswer = new AnswerModel();
            newAnswer.setQuestion(mostrarQuestions);
            newAnswer.setRespuesta(answer.getRespuesta());
            answerService.crearRespuesta(newAnswer);
            
	        List<AnswerModel> listAnswers = answerService.respuestaPregunta(id);;

	        viewModel.addAttribute("listaDeAnswers", listAnswers);
            viewModel.addAttribute("questionId", id);

            return "redirect:/questions/"+id;
        }
    }


}



