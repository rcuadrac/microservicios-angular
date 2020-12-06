package com.cuadra.cursoangular.app.answers.service.api;

import com.cuadra.cursoangular.app.answers.models.entity.Answer;

public interface AnswerService {
	
	Iterable<Answer> saveAll(Iterable<Answer> answers);
	Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId);
	Iterable<Long> findAnsweredExamsIdsByStudent(Long studentId);
	
}
