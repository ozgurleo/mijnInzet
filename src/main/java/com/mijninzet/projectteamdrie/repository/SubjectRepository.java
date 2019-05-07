package com.mijninzet.projectteamdrie.repository;
import com.mijninzet.projectteamdrie.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Task,Integer>{

 @Query(value="SELECT * from subject WHERE subject_id NOT IN (SELECT subject_subject_id from subject_application);", nativeQuery = true)
 List<Task> getSubjects();

 }
