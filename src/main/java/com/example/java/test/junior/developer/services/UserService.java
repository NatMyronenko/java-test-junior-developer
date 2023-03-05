package com.example.java.test.junior.developer.services;


import com.example.java.test.junior.developer.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService  {

    //відповідає за те, що саме обробляється

    private List<User> users = new ArrayList<>();
    private long ID =0;

    //получение списка всех вопросов
    public List<User> listUsers(){
        return users ;
    }

    public void saveUser(User user){
        user.setId(++ID);
        users.add(user);
    }

}
