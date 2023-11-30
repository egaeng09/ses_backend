package com.web.back;

import java.util.TimeZone;

import jakarta.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {
	@PostConstruct
    void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Success");
//		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
	}

}
