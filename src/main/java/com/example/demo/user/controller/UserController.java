package com.example.demo.user.controller;

import com.example.demo.user.dto.SignUpForm;
import com.example.demo.user.dto.User;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Tag(name = "User", description = "사용자 관련 API")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @Operation(summary = "로그인 페이지")
    @ApiResponse(responseCode = "200", description = "로그인 페이지")
    public String loginPage() {
        log.info("loginPage GET 호출");
        return "login";
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            RedirectAttributes model) {
        log.info("login Post 호출");
        User user = userService.login(email, password);
        model.addFlashAttribute("user", user);
        return "redirect:/";

    }

    @GetMapping("/signup")
    @Operation(summary = "회원가입 페이지")
    @ApiResponse(responseCode = "200", description = "회원가입 페이지")
    public String signUpPage() {
        log.info("signUpPage GET requested");
        return "signup";
    }


    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    public String signUp(SignUpForm signUpFormDto, RedirectAttributes model) {
        log.info("signup Post 호출");
        User user = userService.signUp(signUpFormDto);
        model.addFlashAttribute("user", user);
        return "redirect:/";
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    @ApiResponse(responseCode = "200", description = "로그아웃 성공")
    public String logout(HttpSession session) {
        log.info("logout Post 호출");
        session.invalidate();
        return "redirect:/";
    }
}
