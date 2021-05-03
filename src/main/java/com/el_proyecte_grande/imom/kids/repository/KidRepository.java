package com.el_proyecte_grande.imom.kids.repository;

import com.el_proyecte_grande.imom.kids.model.Kid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KidRepository extends JpaRepository<Kid, Long> {

    List<Kid> findAllByParentId(Long userId);
}
