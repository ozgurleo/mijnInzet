package com.mijninzet.projectteamdrie.service;

import java.util.*;

import com.mijninzet.projectteamdrie.model.entity.user.Role;
import com.mijninzet.projectteamdrie.model.entity.user.RoleEnum;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        System.out.println("gegevens User: " + user.getName() + "Roles: " + user.getRolesOfUser());
        user.setRolesOfUser(new ArrayList<>(user.getRolesOfUser()));
        userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {

        boolean isUserAlreadyExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        // If user is found in database, then then user already exists.
        if(existingUser != null){
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User findById(int id){
        Optional<User> result = userRepository.findById(id);
        User theUser;
        if(result.isPresent()){
            theUser= result.get();
        }else {
            throw new RuntimeException("Did not find Employee");
        }
        return theUser;
    }

    //calculate total available teaching hours of teacher (percentage of fte in hours minus 20% overhead)
    public double calculateTotalAvailableHours(int userId) {
        final double ONE_FTE_IN_HOUR = 1658;
//        double percentage = userRepository.getOne(userId).getFte();
        double totalhours =  ONE_FTE_IN_HOUR * 1.0;
        System.out.println("totalhours in methode calculatetotalavailablehours is " + totalhours);
        double totalhoursMinusOverhead = ONE_FTE_IN_HOUR * 0.8;
        return totalhoursMinusOverhead;
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser( User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }




}