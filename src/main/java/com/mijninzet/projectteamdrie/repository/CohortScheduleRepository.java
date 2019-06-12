package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CohortScheduleRepository extends JpaRepository<CohortSchedule, Integer> {

    List<CohortSchedule> findByCohort(int cohortId);

    //Brahim Code: get id that from date/daprt that is overlapping with other cohort
    @Query(value = " SELECT id FROM mijn_inzet.cohort_schedule WHERE cohort_cohort_id!= :cohortId " +
            "AND date= :dateDay AND daypart= :dayPart AND user_user_id= :teacherId ", nativeQuery = true)
    String getDateDaypartOverlap(@Param("cohortId") Integer cohortId, @Param("dateDay") LocalDate dateDay,
                         @Param("dayPart") String dayPart,@Param("teacherId") Integer teacherId);




    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort;", nativeQuery = true)
    ArrayList<Object[]> getCohorts();

    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort_schedule WHERE cohort_cohort_id IN (select MAX(cohort_cohort_id) FROM mijn_inzet.cohort_schedule) ;", nativeQuery = true)


            List<CohortSchedule> getScheduleLastCohort();

    List<CohortSchedule> getAllByCohort_CohortId(int cohortId);

    List<CohortSchedule> getAllByUserIdAndSubject_SubjectIdAndAndCohortNot(int userId, int subjectId,int cohortId);

    List<CohortSchedule> getCohortScheduleByCohort_CohortIdAndUser_Id(int cohortId, int userId);

    List<CohortSchedule> getCohortScheduleByUser_Id(int userId);


}
