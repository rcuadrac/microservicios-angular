package com.cuadra.cursoangular.app.answers.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cuadra.cursoangular.app.commons.entity.Question;
import com.cuadra.cursoangular.app.commons.entity.Student;

import lombok.Data;

@Entity
@Table(name = "answers")
@Data
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Question question;
	

}
