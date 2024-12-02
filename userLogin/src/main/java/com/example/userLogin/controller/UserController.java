package com.example.userLogin.controller;

import com.example.userLogin.dto.request.UserSignupRequestDto;
import com.example.userLogin.dto.request.UserLoginRequestDto;
import com.example.userLogin.dto.response.UserResponseDto;
import com.example.userLogin.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // userService에 대한 멤버를 사용 가능
public class UserController {

    // 생성자 주입
    private final UserService userService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/user/signup")
    public String joinForm() {
        return "join"; // 회원가입 HTML 페이지 호출
    }

    // 회원가입 처리
    @PostMapping("/user/save")
    public String save(@ModelAttribute UserSignupRequestDto userSignupRequestDto) {
        System.out.println("UserController.save");
        System.out.println("userSignupRequestDto = " + userSignupRequestDto);
        userService.signup(userSignupRequestDto); // 서비스 계층 호출

        return "login"; // 회원가입 성공 시 로그인 페이지로 이동
    }

    // 로그인 페이지 출력 요청
    @GetMapping("/user/login")
    public String loginForm() {
        return "login"; // 로그인 HTML 페이지 호출
    }

    // 로그인 처리
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserLoginRequestDto userLoginRequestDto, HttpSession session) {
        UserResponseDto loginResult = userService.login(userLoginRequestDto);
        if (loginResult != null) {
            // 로그인 성공 시 세션에 사용자 ID 저장
            session.setAttribute("loginID", loginResult.getUserId());
            session.removeAttribute("loginFailed");
            return "main"; // 메인 페이지로 이동
        } else {
            // 로그인 실패 시 실패 플래그 설정
            session.setAttribute("loginFailed", true);
            return "login"; // 다시 로그인 페이지로 이동
        }
    }
}