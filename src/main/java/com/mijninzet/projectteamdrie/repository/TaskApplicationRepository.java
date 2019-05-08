package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskApplicationRepository extends JpaRepository<TaskApplication, Integer> {


}
