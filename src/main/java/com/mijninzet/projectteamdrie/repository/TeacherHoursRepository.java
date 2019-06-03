package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherHoursRepository extends JpaRepository<TeacherHours, Integer> {

    int getByUserId();




}
