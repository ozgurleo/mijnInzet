//package com.mijninzet.projectteamdrie.service;
//
//import com.mijninzet.projectteamdrie.repository.TeacherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TeacherService {
//
//    @Autowired
//    TeacherRepository teacherRepository;
//
//    static double ONE_FTE_IN_HOUR_ = 1658;
//
//    //calculate total available teaching hours of teacher (percentage of fte in hours minus 20% overhead)
//    public double calculateTotalAvailableHours(int userId) {
//        double totalhoursMinusOverhead = 0;
//
//       int percentage =  teacherRepository.getById(userId).getPercentageAanstelling();
//       double totalhours = (ONE_FTE_IN_HOUR_/100) * percentage;
//       totalhoursMinusOverhead = totalhours *0.8;
//
//        return totalhoursMinusOverhead;
//
//
//    }
//
//
//}
