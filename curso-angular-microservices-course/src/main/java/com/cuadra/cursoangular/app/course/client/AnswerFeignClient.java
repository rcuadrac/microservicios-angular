package com.cuadra.cursoangular.app.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "answers-service")
public interface AnswerFeignClient {

	@GetMapping("/student/{studentId}/answered-exams")
	Iterable<Long> getExamsIdWithAnswers(@PathVariable Long studentId);
	
}
