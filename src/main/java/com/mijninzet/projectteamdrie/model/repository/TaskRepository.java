package com.mijninzet.projectteamdrie.model.repository;
import com.mijninzet.projectteamdrie.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{

// @Query(value="SELECT * from task WHERE 'task_id' NOT IN (SELECT 'task_task_id' from task_application)", nativeQuery = true)
//List<Task>  getVacancies();


 }
