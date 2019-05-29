package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.SubjectPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectPreferenceRepository extends JpaRepository<SubjectPreference, Integer> {

SubjectPreference save();

}
