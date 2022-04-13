package com.autogarage.eindopdracht;

import com.autogarage.eindopdracht.Service.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EindopdrachtApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JWTService jwtService() {
		return new JWTService();
	}


	public static void main(String[] args) {
		SpringApplication.run(EindopdrachtApplication.class, args);
	}

}
