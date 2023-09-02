package com.camila.dojo.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
















@Entity
@Table(name="answers")
public class AnswerModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = " Por favor ingresa una respuesta")
	@Column(columnDefinition = "text")
	private String respuesta;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	
	//RELACION UNO A MUCHOS CON QUESTION
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QuestionsModel question;
	
	//CONSTRUCTOR VACIO
	public AnswerModel() {
	

}
	
	
	//GETTERS Y SETTERS
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public QuestionsModel getQuestion() {
			return question;
		}
		
		public void setQuestion(QuestionsModel question) {
			this.question = question;
		}	
		
		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}

		@PreUpdate
		protected void onUpdate() {
			this.updatedAt = new Date();
		}


		public String getRespuesta() {
			return respuesta;
		}


		public void setRespuesta(String respuesta) {
			this.respuesta = respuesta;
		}


	

}
