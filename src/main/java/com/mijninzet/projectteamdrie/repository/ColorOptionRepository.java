package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.ColorOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorOptionRepository extends JpaRepository<ColorOption,String> {
}
