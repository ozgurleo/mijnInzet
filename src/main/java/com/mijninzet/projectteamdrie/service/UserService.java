package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){

        List<User> users=new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;

    }
    public Optional<User> getUser(int id){
       return userRepository.findById(id);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(int id,User user){
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public User findByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
