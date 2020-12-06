package com.cuadra.cursoangular.app.user.service.api;

import java.util.List;

import com.cuadra.cursoangular.app.commons.entity.Student;
import com.cuadra.cursoangular.app.commons.service.api.GenericService;

public interface StudentService extends GenericService<Student>{
	
	public List<Student> findByNameOrLastName(String searchParam);

}
