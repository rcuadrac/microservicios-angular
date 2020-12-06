package com.cuadra.cursoangular.app.course.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuadra.cursoangular.app.commons.service.impl.GenericServiceImpl;
import com.cuadra.cursoangular.app.course.client.AnswerFeignClient;
import com.cuadra.cursoangular.app.course.entity.Course;
import com.cuadra.cursoangular.app.course.repository.api.CourseRepository;
import com.cuadra.cursoangular.app.course.service.api.CourseService;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, CourseRepository> implements CourseService {

	@Autowired
	private AnswerFeignClient answerFeignClient;
	
	@Override
	@Transactional(readOnly = true)
	public Course findCourseByStudentId(Long id) {
		return repository.findCourseByStudentId(id);
	}

	@Override
	public Iterable<Long> getExamsIdWithAnswers(Long studentId) {
		return answerFeignClient.getExamsIdWithAnswers(studentId);
	}


}
