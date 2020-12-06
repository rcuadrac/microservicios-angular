package com.cuadra.cursoangular.app.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class CursoAngularMicroservicesEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoAngularMicroservicesEurekaServerApplication.class, args);
	}

}
