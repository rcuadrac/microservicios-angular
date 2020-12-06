package com.cuadra.cursoangular.app.commons.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="questions")
@Getter
@Setter
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@JsonIgnoreProperties(value = {"questions"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="exam_id")
	private Exam exam;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Question)) {
			return false;
		}
		
		Question q = (Question) obj;
		return this.id != null && this.id.equals(q.getId());
	}
	
}
