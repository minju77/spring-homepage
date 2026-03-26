package com.example.demo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입 페이지
	@GetMapping("/user/signup")
	public String signupForm() {
		return "user/signup";
	}
	
	// 회원가입
	@PostMapping("/user/signup")
	public String signup(User user) {
		
		userService.signup(user);
		
		return "redirect:/user/login";
	}
	
	// 로그인 페이지
	@GetMapping("/user/login")
	public String loginForm(HttpSession session, Model model) {
		
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			model.addAttribute("message", "이미 로그인 상태입니다.");
		}
		
		return "user/login";
	}
	
	// 로그인 처리
	@PostMapping("/user/login")
	public String login(String username, String password,
						HttpSession session, Model model) {
		
		User user = userService.login(username, password);
		
		if(user == null) {
			model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
			return "user/login";
		}
		
		// 로그인 성공
		session.setAttribute("loginUser", user);
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
}
