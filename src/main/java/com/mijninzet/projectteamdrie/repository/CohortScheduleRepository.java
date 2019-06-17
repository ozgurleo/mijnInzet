package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
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


    //Brahim Code: get id that from date/daprt that is overlapping with other cohort
    @Query(value = " SELECT id FROM mijn_inzet.cohort_schedule WHERE cohort_cohort_id!= :cohortId " +
            "AND date= :dateDay AND daypart= :dayPart AND user_user_id= :teacherId ", nativeQuery = true)
    String getDateDaypartOverlap(@Param("cohortId") Integer cohortId, @Param("dateDay") LocalDate dateDay,
                         @Param("dayPart") String dayPart,@Param("teacherId") Integer teacherId);

    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.cohort;", nativeQuery = true)
    ArrayList<Object[]> getCohorts();

    //SELECT user_user_id FROM mijn_inzet.cohort_schedule where date= :dayDate AND daypart= :dayPart AND cohort_cohort_id= :teacherId;
    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT user_user_id FROM mijn_inzet.cohort_schedule where date= :dayDate AND " +
            "daypart= :dayPart AND cohort_cohort_id= :cohortId ", nativeQuery = true)
    String getTeacherAtDateAndDayPart(@Param("dayDate") LocalDate dayDate, @Param("dayPart") String dayPart, @Param("cohortId") Integer cohortId);

    //Brahim Code: get schedule belonging to the latest cohort
    @Query(value="SELECT * FROM mijn_inzet.cohort_schedule WHERE cohort_cohort_id IN (select MAX(cohort_cohort_id) FROM mijn_inzet.cohort_schedule) ;", nativeQuery = true)
    List<CohortSchedule> getScheduleLastCohort();


    @Modifying
    @Query(value = "UPDATE mijn_inzet.cohort_schedule SET user_user_id = :teacherId  WHERE id= :scheduleId ", nativeQuery = true)
    @Transactional
    void assignTeacherToSubject(@Param("teacherId") Integer teacherId, @Param("scheduleId") Integer scheduleId);

    @Query(value= "SELECT DISTINCT weeknr FROM mijn_inzet.cohort_schedule WHERE cohort_cohort_id=:cohortId ", nativeQuery = true)
    List<Integer> getDistinctWeeknumbersWhereCohortIdIs(@Param("cohortId") Integer cohortId);

    @Query(value= "SELECT DISTINCT weeknr FROM mijn_inzet.cohort_schedule;", nativeQuery = true)
    List<Integer> getDistinctWeeknumbers();
    List<CohortSchedule> getAllByCohort_CohortId(int cohortId);
    List<CohortSchedule> getAllByUserIdAndSubject_SubjectIdAndCohort_CohortIdIsNot(int userId, int subjectId,int cohortId);
    List<CohortSchedule> getAllByWeeknr(int weeknr);

    @Modifying
    @Query(value = "UPDATE mijn_inzet.cohort_schedule SET subject_subject_id = :subjectId  WHERE id = :id", nativeQuery = true)
    @Transactional
    void assignSubjectToCohort(@Param("subjectId") Integer subjectId, @Param("id") Integer id);








}
