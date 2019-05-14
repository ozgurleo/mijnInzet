package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
 //User findByUsernameAndPassword(String username, String password);

}
