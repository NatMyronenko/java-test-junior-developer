package com.example.java_test_junior_developer.controllers;

import com.example.java_test_junior_developer.model.User;
import com.example.java_test_junior_developer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/main")
    public String api(@RequestParam(name="name", required=false, defaultValue="Natalia") String name, Model model) {
        model.addAttribute("name", name);
        //TODO добавить сколько раз был пройден тест
        return "main";
    }

    @GetMapping("/last")
    public String lastPage(Model model){
        //TODO add users data name , result of test and bals
        return "last";
    }
    @GetMapping("/main/new_user")
    public String addNewUser(Model model){
      //  Iterable<User> users = userService.getAllUsers();
      //  model.addAttribute("users",users);
        return "new_user";
    }

//    @GetMapping("/users")
//    public List<User> showAllUsers(){
//        List<User> users = userService.getAllUsers();
//        return "users";
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
