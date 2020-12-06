package com.cuadra.cursoangular.app.answers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cuadra.cursoangular.app.answers.models.entity.Answer;
import com.cuadra.cursoangular.app.answers.service.api.AnswerService;

@RestController
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Iterable<Answer> answers) {
		Iterable<Answer> answersDb = answerService.saveAll(answers);
		return ResponseEntity.status(HttpStatus.CREATED).body(answersDb);
	}
	
	@GetMapping("/student/{studentId}/exam/{examId}")
	public ResponseEntity<?> getAnswersByStudentByExam(@PathVariable Long studentId, @PathVariable Long examId) {
		Iterable<Answer> answers = answerService.findAnswerByStudentByExam(studentId, examId);
		return ResponseEntity.ok(answers);
	}
	
	@GetMapping("/student/{studentId}/answered-exams")
	public ResponseEntity<?> getExamsIdWithAnswers(@PathVariable Long studentId) {
		Iterable<Long> examsIds = answerService.findAnsweredExamsIdsByStudent(studentId);
		return ResponseEntity.ok(examsIds);
	}
	
}
