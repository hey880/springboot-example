package com.study.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.spring.form.MemberForm;

@Component // Spring Bean으로 등록됨
public class MemberValidator implements Validator { // Validator 인터페이스를 구현

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberForm.class.isAssignableFrom(clazz); // 이 validator가 memberForm 객체를 검증할 수 있는지 확인
    }

    // @Override
    // public void validate(Object target, Errors errors) {
    //     MemberForm form = (MemberForm) target; // 검증 대상 객체를 MemberForm 형으로 변환
    //     // 이름이 빈 값이면 name 필드에 오류를 추가한다.
    //     if (form.getName() == null || form.getName().isBlank()) {
    //         errors.rejectValue("name", "required", "이름을 입력하세요.");
    //     }
    //     // 비밀번호 길이가 4보다 작으면 password 필드에 오류를 추가한다.
    //     if (form.getPassword() == null || form.getPassword().length() < 4) {
    //         errors.rejectValue("password", "short", "비밀번호는 4자 이상 입력하세요.");
    //     }
    // }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors,
            "name",
            "required",
            "이름을 입력하세요."
        );

        MemberForm form = (MemberForm) target;

        if (form.getPassword() == null || form.getPassword().length() < 4) {
            errors.rejectValue("password", "short", "비밀번호는 4자 이상 입력하세요.");
        }
    }
}
