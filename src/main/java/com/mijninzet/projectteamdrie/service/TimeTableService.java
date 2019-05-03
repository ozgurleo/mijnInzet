package com.mijninzet.projectteamdrie.service;


import com.mijninzet.projectteamdrie.model.entity.TimeTable;
import com.mijninzet.projectteamdrie.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableService {

    @Autowired
    TimeTableRepository timeTableRepository;

    public List<TimeTable> getAllTimeTables() {
        ArrayList<TimeTable> timeTables = new ArrayList<>();
        timeTableRepository.findAll().forEach(timeTables::add);
        return timeTables;
    }

    public List<Year> findAllByYear(int year) {
        List<Year> listByYear = new ArrayList<>();
        listByYear = timeTableRepository.findAllByYear(year);
        System.out.println("find all by year" + listByYear);
        return listByYear;
    }

}
