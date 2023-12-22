package com.user.service;

import com.user.model.Role;
import com.user.model.SystemUserEntity;
import com.user.model.UserEntity;
import com.user.repository.SystemUserRepository;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDbInitService {

    private final SystemUserRepository systemUserRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserDbInitService(SystemUserRepository systemUserRepository, UserRepository userRepository) {
        this.systemUserRepository = systemUserRepository;
        this.userRepository = userRepository;
    }

    public boolean initDatabase() {
        for(SystemUserEntity user:getSystemUserList()){
            if(!systemUserRepository.existsByUserName(user.getUserName())){
                systemUserRepository.save(user);
            }
        }
        initUserEntityDatasets();
        return true;
    }

    public List<SystemUserEntity> getSystemUserList(){
        List<SystemUserEntity>userList= new ArrayList<>();
        PasswordEncoder pw = new BCryptPasswordEncoder();
        userList.add(new SystemUserEntity("admin",pw.encode("test123")));
        userList.add(new SystemUserEntity("superAdmin",pw.encode("test123")));
        userList.add(new SystemUserEntity("user",pw.encode("test123")));
        userList.add(new SystemUserEntity("guest",pw.encode("test123")));
        return userList;
    }
    public boolean initUserEntityDatasets() {
        PasswordEncoder pw = new BCryptPasswordEncoder();
        if (!userRepository.existsByEmail("guest@guest.de")) {

            userRepository.save(new UserEntity("guest", "guest", "gugu", "1223444", "guest@guest.de", pw.encode("test"), Role.USER));
        }

        if (!userRepository.existsByEmail("admin@admin.de")) {
            userRepository.save(new UserEntity("admin", "admin", "adad", "1223444", "admin@admin.de", pw.encode("test"), Role.ADMIN));
        }
        return userRepository.findAll().size() > 1;
    }
}
