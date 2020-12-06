package com.cuadra.cursoangular.app.exams.service.api;

import java.util.List;

import com.cuadra.cursoangular.app.commons.entity.Exam;
import com.cuadra.cursoangular.app.commons.entity.Subject;
import com.cuadra.cursoangular.app.commons.service.api.GenericService;

public interface ExamService extends GenericService<Exam>{
	
	List<Exam> findByName(String term);
	
	List<Subject> findAllSubjects();

}
