package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    //Brahim Code: get all subject names
    @Query(value="SELECT * FROM mijn_inzet.subject;", nativeQuery = true)
    ArrayList<Object[]> getSubjects();

    //Brahim Code: get all classrooms
    @Query(value="SELECT * FROM mijn_inzet.temp_classroom;", nativeQuery = true)
    ArrayList<Object[]> getRooms();



}
