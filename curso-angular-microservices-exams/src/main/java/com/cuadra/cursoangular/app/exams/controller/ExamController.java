package com.cuadra.cursoangular.app.exams.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cuadra.cursoangular.app.commons.controller.GenericController;
import com.cuadra.cursoangular.app.commons.entity.Exam;
import com.cuadra.cursoangular.app.commons.entity.Question;
import com.cuadra.cursoangular.app.exams.service.api.ExamService;

@RestController
public class ExamController extends GenericController<Exam, ExamService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.valid(result);
		}
		
		Optional<Exam> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}		
		Exam examDb = optional.get();
		examDb.setName(exam.getName());
		
		List<Question> questionsToDelete = examDb.getQuestions()
			.stream().filter(qtd -> !exam.getQuestions().contains(qtd))
			.collect(Collectors.toList());
		
		questionsToDelete.forEach(examDb::removeQuestion);
		
		examDb.setQuestions(exam.getQuestions());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDb));
	}
	
	@GetMapping("/find-by/{term}")
	public ResponseEntity<?> findByName(@PathVariable String term) {
		return ResponseEntity.ok(service.findByName(term));
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<?> retrieveSubjects() {
		return ResponseEntity.ok(service.findAllSubjects());
	}

}
