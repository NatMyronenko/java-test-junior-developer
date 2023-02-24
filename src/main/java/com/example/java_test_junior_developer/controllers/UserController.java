package com.example.java_test_junior_developer.controllers;

import com.example.java_test_junior_developer.model.User;
import com.example.java_test_junior_developer.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
        //   model.addAttribute("name", name);
        //TODO добавить сколько раз был пройден тест
        return "main";
    }
//  last page
//TODO add users data name,surname , result of test and bals

    @GetMapping("/last")
    public String lastPage(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String userSurname = request.getParameter("userSurname");
        userName = "Пан(i)" + userName + userSurname;
        model.addAttribute("nameAttribute", userName);
        //     model.addAttribute("bals",getBals);
        return "last";
//    @GetMapping("/last")
//    public String lastPage(@RequestParam("userName") String userName, @RequestParam("userSurname")
//    String userSurname, Model model) {
//        userName = "Пан(i)" + userName + userSurname;
//        model.addAttribute("nameAttribute", userName);
//        return "last";
    }

    // page for admin
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> showAllUsers = userService.getAllUsers();
        model.addAttribute("allUsers", showAllUsers);
        return "users";
    }

//
//    @GetMapping("/main/new_user")
//    public String addNewUser(Model model) {
//        //  Iterable<User> users = userService.getAllUsers();
//        //  model.addAttribute("users",users);
//        return "new_user";
//    }

//    @GetMapping("/show-details")
//    public String showUserDetails(){
//        return "show-user-details";
//    }
}
//@RestController
//@RequestMapping("/api")
//public class UserRestController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/users")
//    public List<User> showAllUsers(){
//        List<User> users = userService.getAllUsers();
//        return users;
//    }
//    @PostMapping("/users")
//    public User addNewUser(@RequestBody User user) {
//        userService.saveUser(user);
//        return user;
//    }
//    @PutMapping("/users")
//    public User updateUser(@RequestBody User user) {
//        userService.saveUser(user);
//        return user;
//    }
//
//    @DeleteMapping("/users/{id}")
//    public String deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//        return "Employee with ID =" + id + "  was deleted";
//    }
//    //------------------
////    @PutMapping("/page/{page}")
////    public String User updateBals(@RequestBody User bals){
////        userService.saveUser(bals);
////        return "page_2";
//
//
//}
