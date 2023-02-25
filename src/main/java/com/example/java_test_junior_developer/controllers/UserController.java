package com.example.java_test_junior_developer.controllers;

import com.example.java_test_junior_developer.models.User;
import com.example.java_test_junior_developer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/java-test")
public class UserController {

    private final UserService userService;

    //  1 page
    @GetMapping("/ask-details")
    public String askUserDetails(Model model) {
        model.addAttribute("user", new User());
        return "ask-details";
    }
// 2 page

    @GetMapping("/main")
    public String api(Model model) {
        //TODO добавить сколько раз был пройден тест
        return "main";
    }
//  last page
//TODO add users data name,surname , result of test and bals

    //    @GetMapping("/last")
//    public String lastPage(HttpServletRequest request, Model model) {
//        String userName = request.getParameter("userName");
//        String userSurname = request.getParameter("userSurname");
//        userName = "Пан(i)" + userName + userSurname;
//        model.addAttribute("nameAttribute", userName);
//        //     model.addAttribute("bals",getBals);
//        return "last";
    @GetMapping("/last")
    public String lastPage(@ModelAttribute("user") User user) {
//        String name = user.getName();
//        user.setName("Mr "+ name);
//        String surname = user.getSurname();
//        user.setSurname(surname + ".");;
        return "last";
    }

    // page for admin
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> showAllUsers = userService.getAllUsers();
        model.addAttribute("allUsers", showAllUsers);
        return "users";
    }
}
