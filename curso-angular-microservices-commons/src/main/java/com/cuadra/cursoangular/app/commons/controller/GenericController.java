package com.cuadra.cursoangular.app.commons.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cuadra.cursoangular.app.commons.service.api.GenericService;

public class GenericController<E,S extends GenericService<E>> {

	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> findAll(Pageable pageable) {
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<E> entity = service.findById(id);
		if(entity.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity.get());
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result) {
		if(result.hasErrors()) {
			return this.valid(result);
		}
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	protected ResponseEntity<?> valid(BindingResult result) {
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> errors.put(err.getField(), 
				String.format("El campo %s %s", err.getField(), err.getDefaultMessage())));
		
		return ResponseEntity.badRequest().body(errors);
	}
	
}
