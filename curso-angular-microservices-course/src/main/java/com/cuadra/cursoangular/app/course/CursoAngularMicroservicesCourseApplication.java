package com.cuadra.cursoangular.app.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.cuadra.cursoangular.app.commons.entity",
	"com.cuadra.cursoangular.app.course.entity"})
public class CursoAngularMicroservicesCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoAngularMicroservicesCourseApplication.class, args);
	}

}
