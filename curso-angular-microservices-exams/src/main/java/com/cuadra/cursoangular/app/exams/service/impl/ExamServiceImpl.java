package com.cuadra.cursoangular.app.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuadra.cursoangular.app.commons.entity.Exam;
import com.cuadra.cursoangular.app.commons.entity.Subject;
import com.cuadra.cursoangular.app.commons.service.impl.GenericServiceImpl;
import com.cuadra.cursoangular.app.exams.repository.ExamRepository;
import com.cuadra.cursoangular.app.exams.repository.SubjectRepository;
import com.cuadra.cursoangular.app.exams.service.api.ExamService;

@Service
public class ExamServiceImpl extends GenericServiceImpl<Exam, ExamRepository> implements ExamService{

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Exam> findByName(String term) {
		return repository.findByName(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subject> findAllSubjects() {
		return (List<Subject>) subjectRepository.findAll();
	}

}
