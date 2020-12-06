package com.cuadra.cursoangular.app.course.service.api;

import com.cuadra.cursoangular.app.commons.service.api.GenericService;
import com.cuadra.cursoangular.app.course.entity.Course;

public interface CourseService extends GenericService<Course>{
	
	public Course findCourseByStudentId(Long id);
	Iterable<Long> getExamsIdWithAnswers(Long studentId);
	
}
