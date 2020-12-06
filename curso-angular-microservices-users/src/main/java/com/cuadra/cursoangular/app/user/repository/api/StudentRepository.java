package com.cuadra.cursoangular.app.user.repository.api;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuadra.cursoangular.app.commons.entity.Student;


public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	@Query("select s from Student s where s.name like %?1% or s.lastName like %?1%")
	public List<Student> findByNameOrLastName(String searchParam);

}
