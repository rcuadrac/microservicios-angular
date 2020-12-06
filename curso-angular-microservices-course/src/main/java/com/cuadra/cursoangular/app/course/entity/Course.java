package com.cuadra.cursoangular.app.course.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cuadra.cursoangular.app.commons.entity.Exam;
import com.cuadra.cursoangular.app.commons.entity.Student;

import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Student> students;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Exam> exams;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Course() {
		this.students = new ArrayList<>();
		this.exams = new ArrayList<>();
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	
	public void addExam(Exam exam) {
		this.exams.add(exam);
	}
	
	public void removeExam(Exam exam) {
		this.exams.remove(exam);
	}
	
	
}
