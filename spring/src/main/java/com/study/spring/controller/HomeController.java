package com.study.spring.controller;
// @Controller를 사용하기 위한 클래스
import org.springframework.stereotype.Controller;
// Model은 Controller에서 화면으로 데이터를 전달할 때 사용
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // @Controller는 이 클래스가 웹 요청을 처리하는 클래스임을 나타낸다.
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // message라는 이름으로 문자열 데이터를 화면에 전달한다.
        model.addAttribute("message", "Spring Boot 4.1.1 시작하기");
        return "home"; // home이라는 이름의 Thymeleaf 템플릿을 찾아 화면으로 출력한다.
    }
}