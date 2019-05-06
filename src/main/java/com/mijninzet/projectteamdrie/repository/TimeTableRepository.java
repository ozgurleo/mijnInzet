package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {



}
