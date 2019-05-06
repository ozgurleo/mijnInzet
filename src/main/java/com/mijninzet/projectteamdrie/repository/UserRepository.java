package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
public User findByUsernameAndPassword(String username, String password);

}
