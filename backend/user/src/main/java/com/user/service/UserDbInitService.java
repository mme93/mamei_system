package com.user.service;

import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDbInitService {

    private final UserRepository userRepository;

    @Autowired
    public UserDbInitService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean initDatabase() {
        for(User user:getUserList()){
            if(!userRepository.existsByUserName(user.getUserName())){
                userRepository.save(user);
            }
        }
        return true;
    }

    public List<User> getUserList(){
        List<User>userList= new ArrayList<>();
        userList.add(new User("admin","test123"));
        userList.add(new User("superAdmin","test123"));
        userList.add(new User("user","test123"));
        userList.add(new User("guest","test123"));
        return userList;
    }
}
