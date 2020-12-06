package com.cuadra.cursoangular.app.commons.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exams")
public class Exam {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@NotEmpty
	private String name;
	
	@Getter
	@Setter
	@Column(name="create_At")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Getter
	@JsonIgnoreProperties(value = {"exam"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exam")
	private List<Question> questions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Subject subject;
	
	@Getter
	@Setter
	@Transient
	private boolean answered;
	
	public Exam() {
		this.questions = new LinkedList<>();
	}
	
	@PrePersist
	public void prePersist( ) {
		this.createAt = new Date();
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
		question.setExam(this);
	}
	
	public void removeQuestion(Question question) {
		this.questions.remove(question);
		question.setExam(null);
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions.clear();
		questions.forEach(this::addQuestion);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Exam)) {
			return false;
		}
		
		Exam e = (Exam) obj;
		return this.id != null && this.id.equals(e.getId());
	}
}
