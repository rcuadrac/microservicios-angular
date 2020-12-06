package com.cuadra.cursoangular.app.exams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuadra.cursoangular.app.commons.entity.Exam;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long>{
	
	@Query("select e from Exam e where e.name like %?1%")
	List<Exam> findByName(String term);
	

}
