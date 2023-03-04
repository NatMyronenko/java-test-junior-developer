//package com.example.java.test.junior.developer.services;
//
//
//import com.example.java.test.junior.developer.models.User;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class UserService  {
//
//    private List<User> users = new ArrayList<>();
//    private long ID =0;
//    {
//        users.add(new User(++ID,"Tom","Ivanov",80));
//        users.add(new User(++ID,"Ivan","Karpenko",90));
//    }
//    //получение списка всех вопросов
//    public List<User> listUsers(){
//        return users ;
//    }
//    public void saveUser(User user){
//        user.setId(++ID);
//        users.add(user);
//    }
////    public void deleteUsers(Long id){
////        users.removeIf(question -> question.getId().equals(id));
////    }
////
////    public User getUserById(Long id) {
////        for (User users : users) {
////            if (users.getId().equals(id)) return users;
////        }
////        return null;
// //   }
//}
