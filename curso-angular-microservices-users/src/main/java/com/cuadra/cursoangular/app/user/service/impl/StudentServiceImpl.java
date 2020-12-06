 package com.cuadra.cursoangular.app.user.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cuadra.cursoangular.app.commons.entity.Student;
import com.cuadra.cursoangular.app.commons.service.impl.GenericServiceImpl;
import com.cuadra.cursoangular.app.user.repository.api.StudentRepository;
import com.cuadra.cursoangular.app.user.service.api.StudentService;


@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, StudentRepository> implements StudentService {

	@Override
	public List<Student> findByNameOrLastName(String searchParam) {
		return repository.findByNameOrLastName(searchParam);
	}
	
	
}
