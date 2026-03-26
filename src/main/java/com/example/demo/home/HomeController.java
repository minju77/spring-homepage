package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.example.demo.user.User;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loginUser");

        model.addAttribute("loginUser", user);

        return "home";
    }

}