package it.course.myrecipesc4;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackageClasses = { 
//		Myrecipesc4Application.class,
//		Jsr310JpaConverters.class 
//})
public class Myrecipesc4Application {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Myrecipesc4Application.class, args);
	}

}
