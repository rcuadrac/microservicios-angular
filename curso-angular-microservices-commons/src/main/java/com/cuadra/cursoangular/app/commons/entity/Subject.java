package com.cuadra.cursoangular.app.commons.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "subjects")
@Data
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@JsonIgnoreProperties(value = {"childs"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Subject parent;
	
	@JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Subject> childs;

	public Subject() {
		this.childs = new ArrayList<>();
	}
	
}
