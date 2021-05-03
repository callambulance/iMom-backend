package com.el_proyecte_grande.imom.pregnancy_info.repository;

import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfo;
import com.el_proyecte_grande.imom.pregnancy_info.model.WeightDuringPregnancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PregnancyInfoRepository extends JpaRepository<PregnancyInfo, Long> {

    PregnancyInfo findByUserId(Long userId);
}
