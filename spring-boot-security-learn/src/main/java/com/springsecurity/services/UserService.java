package com.springsecurity.services;

import com.springsecurity.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> list=new ArrayList<>();

    public UserService() {
        list.add(new User("abc","abc","abc@gmail.com","ADMIN"));
        list.add(new User("xyz","xyz","xyz@gmail.com","NORMAL"));

    }
    //Get All Users
    public  List<User> getAllUsers(){
        return this.list;
    }

    //Get Single User
    public  User getUser(String username){
        return this.list.stream().filter((user) ->user.getUsername().equals(username) ).findAny().orElse(null);
    }

    //Add New User
    public  User addUser(User user){
        list.add(user);
        return user;
    }

}
