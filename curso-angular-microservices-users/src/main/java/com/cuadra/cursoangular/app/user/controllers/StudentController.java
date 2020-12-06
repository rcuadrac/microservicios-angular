package com.cuadra.cursoangular.app.user.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cuadra.cursoangular.app.commons.controller.GenericController;
import com.cuadra.cursoangular.app.commons.entity.Student;
import com.cuadra.cursoangular.app.user.service.api.StudentService;

@RestController
public class StudentController extends GenericController<Student, StudentService> {
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Student student, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.valid(result);
		}

		Optional<Student> studentOptional = service.findById(id);
		if(studentOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Student studentDb = studentOptional.get();
		studentDb.setName(student.getName());
		studentDb.setLastName(student.getLastName());
		studentDb.setEmail(student.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
		
	}
	
	@GetMapping("/search-by/{filter}")
	public ResponseEntity<?> searchBy(@PathVariable String filter){
		return ResponseEntity.ok(service.findByNameOrLastName(filter));
	}

	@PostMapping("/save-image")
	public ResponseEntity<?> saveImage(@Valid Student student, BindingResult result, @RequestParam MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			student.setImage(file.getBytes());
		}
		return super.save(student, result);
	}
	
	@PutMapping("/edit-image/{id}")
	public ResponseEntity<?> editImage(@Valid Student student, BindingResult result, @PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
		
		if(result.hasErrors()) {
			return this.valid(result);
		}

		Optional<Student> studentOptional = service.findById(id);
		if(studentOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Student studentDb = studentOptional.get();
		studentDb.setName(student.getName());
		studentDb.setLastName(student.getLastName());
		studentDb.setEmail(student.getEmail());
		
		if(!file.isEmpty()) {
			studentDb.setImage(file.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDb));
		
	}
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> getImage(@PathVariable Long id) {
		Optional<Student> o = service.findById(id);
		if(o.isEmpty() || o.get().getImage() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource image = new ByteArrayResource(o.get().getImage());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
	
}
