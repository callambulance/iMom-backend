package com.el_proyecte_grande.imom.tips.repository;

import com.el_proyecte_grande.imom.tips.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {

    Tip findTipByDate(LocalDate date);
}
