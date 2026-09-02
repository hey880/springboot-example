package com.study.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.spring.form.MemberForm;
import com.study.spring.validator.MemberValidator;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MemberController {
    // Validator 필드 선언
    private final MemberValidator memberValidator;
    
    // Dependency Injection 중 생성자 주입으로 MemberValidator를 받음
    // 1) Spring 컨테이너가 MemberValidator를 Bean으로 만들고
    // 2) Spring이 MemberController 생성 때 이 클래스의 생성자를 보고 MemberValidator 타입 파라미터 필요한 걸 확인
    // 3) Spring 컨테이너의 MemberValidator Bean을 찾아서 생성자 호출 시 인자로 넘긴다.
    // 4) this.memberValidator = memberValidator;가 실행되어 private final MemberValidator memberValidator; 필드 값이 채워짐
    public MemberController(MemberValidator memberValidator) {
        this.memberValidator = memberValidator;
    }

    @InitBinder("memberForm") // memberForm이라는 이름의 binder설정을 적용, 
    public void initBinder(WebDataBinder binder) {
        // WebDataBinder (웹 요청 데이터 바인딩): 클라이언트가 보낸 HTTP 요청 파라미터나 폼 데이터를 자바 객체(Object)로
        // 변환하고 검증(Validation)할 때 사용하는 Spring Framework Data Binding 핵심 컴포넌트
        binder.addValidators(memberValidator);
    }

    @GetMapping("/members/new")
    public String form(Model model) { // 입력창을 보여주기 위해 memberForm객체를 Model에 담음
        model.addAttribute("memberForm", new MemberForm());
        return "member-form";
    }
    
    // 주의! BindingResult는 반드시 @Valid가 붙은 객체 바로 뒤에 위치해야한다.
    @PostMapping("/members/new") // Form 입력값과 검증 결과를 함께 받는다.
    public String submit(@Valid MemberForm memberForm, BindingResult bindingResult, Model model) {
        // BindingResult는 검정 결과를 담는 객체
        // memberValidator.validate(memberForm, bindingResult); // Validator로 입력값 검사
        
        if (bindingResult.hasErrors()) {
            return "member-form"; // 오류가 있으면 다시 입력 화면으로 이동
        }
        // 오류가 없으면 결과 화면으로 이동
        model.addAttribute("member", memberForm);
        return "member-result";
    }
}
