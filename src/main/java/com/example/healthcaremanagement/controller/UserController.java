package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.entity.UserType;
import com.example.healthcaremanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Optional<User> byEmail = userRepository.findUserByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            user.setUserType(UserType.USER);
            userRepository.save(user);
        }
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }
}
