package com.user.service;

import com.user.model.SystemUserEntity;
import com.user.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDbInitService {

    private final SystemUserRepository systemUserRepository;

    @Autowired
    public UserDbInitService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public boolean initDatabase() {
        for(SystemUserEntity user:getSystemUserList()){
            if(!systemUserRepository.existsByUserName(user.getUserName())){
                systemUserRepository.save(user);
            }
        }
        return true;
    }

    public List<SystemUserEntity> getSystemUserList(){
        List<SystemUserEntity>userList= new ArrayList<>();
        userList.add(new SystemUserEntity("admin","test123"));
        userList.add(new SystemUserEntity("superAdmin","test123"));
        userList.add(new SystemUserEntity("user","test123"));
        userList.add(new SystemUserEntity("guest","test123"));
        return userList;
    }
}
