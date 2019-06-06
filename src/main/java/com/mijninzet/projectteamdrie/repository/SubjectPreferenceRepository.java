package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.SubjectPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SubjectPreferenceRepository extends JpaRepository<SubjectPreference, Integer> {
    @Modifying
    @Query(value = "INSERT INTO `mijn_inzet`.`subject_preference` (`preference`, `subject_subject_id`, `user_user_id`) " +
            "VALUES (:preference,:subject_id,:user_id)", nativeQuery = true)

    @Transactional
    void insertPreference(@Param("preference") Integer preference,
                          @Param("subject_id") Integer subject_id,
                          @Param("user_id") Integer user_id);
}
