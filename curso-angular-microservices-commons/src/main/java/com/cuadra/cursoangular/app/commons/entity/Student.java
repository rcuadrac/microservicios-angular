package com.cuadra.cursoangular.app.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -53027810830222187L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Lob
	@JsonIgnore
	private byte[] image;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Integer getImageHashCode() {
		return this.image != null ? this.image.hashCode() : null;
	}

}
