package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface UserService {
     void saveUser(User user);

     boolean isUserAlreadyPresent(User user);

     List<User> getAllUsers();
     void addUser(User user);
     Optional<User> getUser(int id);
    void updateUser(int id,User user);
    void deleteUser(int id);
}
