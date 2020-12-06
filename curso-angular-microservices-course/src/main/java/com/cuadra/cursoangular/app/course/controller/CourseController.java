package com.cuadra.cursoangular.app.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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
import com.cuadra.cursoangular.app.commons.entity.Student;
import com.cuadra.cursoangular.app.course.entity.Course;
import com.cuadra.cursoangular.app.course.service.impl.CourseServiceImpl;

@RestController
public class CourseController extends GenericController<Course,CourseServiceImpl> {

	@Value("${config.balancer.test}")
	private String balancerTest;
	
	@GetMapping("/balancer-test")
	public ResponseEntity<?> loadBalancerTest() {
		Map<String, Object> response = new HashMap<>();
		response.put("balancer", balancerTest);
		response.put("courses", service.findAll());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.valid(result);
		}
		
		Optional<Course> courseToEdit = this.service.findById(id);
		if(!courseToEdit.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		Course dbCourse = courseToEdit.get();
		dbCourse.setName(course.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCourse));
	}
	
	@PutMapping("/{id}/set-student")
	public ResponseEntity<?> setStudent(@PathVariable Long id, @RequestBody List<Student> students) {
		
		Optional<Course> optionalCourse = this.service.findById(id);
		if(!optionalCourse.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = optionalCourse.get();
		students.forEach(s -> courseDb.addStudent(s));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@PutMapping("/{id}/delete-student")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id, @RequestBody Student student) {
		
		Optional<Course> optionalCourse = this.service.findById(id);
		if(!optionalCourse.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = optionalCourse.get();
		courseDb.removeStudent(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> findByStudentId(@PathVariable Long id) {
		Course course = service.findCourseByStudentId(id);
		
		if(null != course) {
			List<Long> examsIds = (List<Long>) service.getExamsIdWithAnswers(id);
			List<Exam> exams = course.getExams().stream()
					.map(exam -> {
						if(examsIds.contains(exam.getId())) {
							exam.setAnswered(true);
						}
						return exam;
					}).collect(Collectors.toList());
			
			course.setExams(exams);
		}
		return ResponseEntity.ok(course);
	}
	
	@PutMapping("/{id}/set-exams")
	public ResponseEntity<?> setExams(@PathVariable Long id, @RequestBody List<Exam> exams) {
		
		Optional<Course> optionalCourse = this.service.findById(id);
		if(!optionalCourse.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = optionalCourse.get();
		exams.forEach(e -> courseDb.addExam(e));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@PutMapping("/{id}/delete-exam")
	public ResponseEntity<?> deleteExam(@PathVariable Long id, @RequestBody Exam exam) {
		
		Optional<Course> optionalCourse = this.service.findById(id);
		if(!optionalCourse.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = optionalCourse.get();
		courseDb.removeExam(exam);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
}
