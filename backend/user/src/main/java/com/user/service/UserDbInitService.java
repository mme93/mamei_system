package com.user.service;

import com.user.model.UserEntity;
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
        for(UserEntity user:getUserList()){
            if(!userRepository.existsByUserName(user.getUserName())){
                userRepository.save(user);
            }
        }
        return true;
    }

    public List<UserEntity> getUserList(){
        List<UserEntity>userList= new ArrayList<>();
        userList.add(new UserEntity("admin","test123"));
        userList.add(new UserEntity("superAdmin","test123"));
        userList.add(new UserEntity("user","test123"));
        userList.add(new UserEntity("guest","test123"));
        return userList;
    }
}
