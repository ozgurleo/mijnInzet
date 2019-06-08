package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CohortScheduleRepository extends JpaRepository<CohortSchedule, Integer> {

    List<CohortSchedule> findByCohort(int cohortId);

    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort;", nativeQuery = true)
    ArrayList<Object[]> getCohorts();

    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort_schedule WHERE cohort_cohort_id IN " +
            "(select MAX(cohort_cohort_id) FROM mijn_inzet.cohort_schedule) ;", nativeQuery = true)
    List<CohortSchedule> getScheduleLastCohort();

    List<CohortSchedule> getAllByUserIdAndSubject_SubjectId(int userId, int subjectId);

//UPDATE cohort_schedule CS SET CS.user_user_id= :teacherId WHERE CS.id=scheduleId;
@Modifying
@Query(value = "UPDATE cohort_schedule CS SET CS.user_user_id= :teacherId WHERE CS.id=:scheduleId", nativeQuery = true)
@Transactional
void storeSchedule(@Param("teacherId") Integer teacherId,@Param("scheduleId") Integer scheduleId);



//Brahim code: get cohortId which is overlapping with given date/userId
@Query(value = "SELECT CS.cohort_cohort_id FROM mijn_inzet.cohort_schedule CS WHERE CS.user_user_id= :teacherId " +
        "AND CS.daypart=:dayPart AND CS.date= :dayDate AND CS.cohort_cohort_id=:cohortId", nativeQuery = true)
int getCohortOverlap(
        @Param("teacherId") Integer teacherId,
        @Param("dayPart") String dayPart,
        @Param("dayDate") LocalDate dayDate,
        @Param("cohortId") Integer cohortId);

}
