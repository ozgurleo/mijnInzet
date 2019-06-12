package com.mijninzet.projectteamdrie.repository;


import com.mijninzet.projectteamdrie.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String role);

}
