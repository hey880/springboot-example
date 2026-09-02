package com.study.spring; // 이 클래스가 속한 기본 패키지를 선언

// Spring Boot 실행에 필요한 클래스를 import
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication은 Spring Boot 애플리케이션의 시작 지점을 의미
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); // run이 실행되면 내장 Tomcat이 실행된다. args는 실행 시 전달되는 옵션값
	}

}
