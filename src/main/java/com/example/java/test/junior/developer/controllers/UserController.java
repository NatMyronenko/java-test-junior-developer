package com.example.java.test.junior.developer.controllers;

import com.example.java.test.junior.developer.models.User;
import com.example.java.test.junior.developer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/java-test")
public class UserController {
    private final UserService userService;

    //  1 page
//    @GetMapping("/ask-details")
//    public String askUserDetails(Model model) {
//        model.addAttribute("user", new User());
//        return "ask-details";
//    }
//// 2 page
//
//    @GetMapping("/main")
//    public String api(Model model) {
//        //TODO добавить сколько раз был пройден тест
//        return "main";
//    }
//
//    @PostMapping("/user/create")
//    public String createUser(User user) {
//        userService.saveUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/last")
//    public String lastPage(@ModelAttribute("user") User user) {
//        return "last";
//    }
//
//    @GetMapping("/users")
//    public String showAllUsers(Model model) {
//        model.addAttribute("users", userService.listUsers());
//        return "users";
//    }
}
