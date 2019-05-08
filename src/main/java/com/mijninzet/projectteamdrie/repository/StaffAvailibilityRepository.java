package com.mijninzet.projectteamdrie.repository;


import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffAvailibilityRepository extends JpaRepository<StaffAvailability,Integer> {


}
