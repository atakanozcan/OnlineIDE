package edu.tum.ase.compiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableOAuth2Client
@EnableResourceServer
public class CompilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompilerApplication.class, args);
	}

}
