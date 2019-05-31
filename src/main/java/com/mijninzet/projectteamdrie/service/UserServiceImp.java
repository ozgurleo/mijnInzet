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

   static double ONE_FTE_IN_HOUR_ = 1658;


    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        user.setRoles(new HashSet<Role>(Arrays.asList(selectRole())));
        userRepository.save(user);
    }

    private Role selectRole() {
        // needs to be connected to frondend with dropdown menu with available roles
        String roleDescriptor = "TEACHER";
        Role userRole;
        switch (roleDescriptor) {
            case "TEACHER":
                userRole = roleRepository.findByRole(RoleEnum.TEACHER.getRoleDescriptor());
                break;
            case "ADMIN":
                userRole = roleRepository.findByRole(RoleEnum.ADMINISTRATOR.getRoleDescriptor());
                break;
            case "MANAGER":
                userRole = roleRepository.findByRole(RoleEnum.MANAGER.getRoleDescriptor());
                break;
            case "SCHEDULER":
                userRole = roleRepository.findByRole(RoleEnum.SCHEDULER.getRoleDescriptor());
                break;
            case "COORDINATOR":
                userRole = roleRepository.findByRole(RoleEnum.COORDINATOR.getRoleDescriptor());
                break;
            default:
                userRole = roleRepository.findByRole(RoleEnum.DEFAULT.getRoleDescriptor());
                break;
        }
        return userRole;
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

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;

    }
    public User findById(int id){

        Optional<User> result = userRepository.findById(id);
        User theUser=null;

        if(result.isPresent()){
            theUser= result.get();
        }else {
            throw new RuntimeException("Did not find Employee");
        }
        return theUser;
    }



    //calculate total available teaching hours of teacher (percentage of fte in hours minus 20% overhead)
    public double calculateTotalAvailableHours(int userId) {
        double totalhoursMinusOverhead;

//        double percentage = userRepository.getOne(userId).getFte();
        double totalhours =  ONE_FTE_IN_HOUR_* 1.0;
        System.out.println("totalhours in methode calculatetotalavailablehours is " + totalhours);
        totalhoursMinusOverhead = totalhours * 0.8;

        return totalhoursMinusOverhead;
    }



    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(int id, User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}