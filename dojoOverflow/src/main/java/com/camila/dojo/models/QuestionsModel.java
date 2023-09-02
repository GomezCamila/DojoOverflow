package com.camila.dojo.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="questions")
public class QuestionsModel {

	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = " Por favor ingresa una pregunta")
	@Column(columnDefinition = "text")
	private String pregunta;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	
	@OneToMany(mappedBy = "question")
	private List<AnswerModel> answers;
	
	//RELACION MUCHOS A MUCHOS HACIA MENSAJES
	@ManyToMany
	@JoinTable(name = "question_tag",
	    joinColumns = @JoinColumn(name = "question_id"),
	    inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<TagsModel> tags;
	

	//CONSTRUCTOR VACIO
	public QuestionsModel() {
		
	}
	
	//GETTERS Y SETTERS
	public List<AnswerModel> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<AnswerModel> answers) {
		this.answers = answers;
	}
	
	public List<TagsModel> getTags() {
		return tags;
	}
	
	public void setTags(List<TagsModel> tags) {
		this.tags = tags;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
