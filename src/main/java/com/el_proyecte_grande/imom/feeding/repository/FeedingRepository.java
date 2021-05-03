package com.el_proyecte_grande.imom.feeding.repository;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedingRepository extends JpaRepository<Feeding, Long> {

    List<Feeding> findAllByUserIdAndKidId(Long userId, Long kidId);
    List<Feeding> findAllByUserId(Long userId);
}
