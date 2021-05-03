package com.el_proyecte_grande.imom.tasks_before_birth.repository;

import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskBeforeBirthRepository extends JpaRepository<TaskBeforeBirth, Long> {

    List<TaskBeforeBirth> findAllByUserId(Long userId);
}
