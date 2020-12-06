package com.cuadra.cursoangular.app.answers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.cuadra.cursoangular.app.commons.entity",
	"com.cuadra.cursoangular.app.answers.models.entity"})
public class CursoAngularMicroservicesAnswersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoAngularMicroservicesAnswersApplication.class, args);
	}

}
