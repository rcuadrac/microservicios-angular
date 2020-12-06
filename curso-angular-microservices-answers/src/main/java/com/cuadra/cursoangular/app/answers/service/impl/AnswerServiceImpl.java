package com.cuadra.cursoangular.app.answers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuadra.cursoangular.app.answers.models.entity.Answer;
import com.cuadra.cursoangular.app.answers.repository.AnswerRepository;
import com.cuadra.cursoangular.app.answers.service.api.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;

	@Transactional
	@Override
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		return answerRepository.saveAll(answers);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId) {
		return answerRepository.findAnswerByStudentByExam(studentId, examId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findAnsweredExamsIdsByStudent(Long studentId) {
		return answerRepository.findAnsweredExamsIdsByStudent(studentId);
	}

}
