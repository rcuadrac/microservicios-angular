package com.cuadra.cursoangular.app.course.repository.api;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuadra.cursoangular.app.course.entity.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
	
	@Query("select c from Course c join fetch c.students s where s.id=?1")
	public Course findCourseByStudentId(Long id);

}
