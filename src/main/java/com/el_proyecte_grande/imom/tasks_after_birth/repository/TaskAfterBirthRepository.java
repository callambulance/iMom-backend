package com.el_proyecte_grande.imom.tasks_after_birth.repository;

import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAfterBirthRepository extends JpaRepository<TaskAfterBirth, Long> {

    List<TaskAfterBirth> findAllByUserId(Long userId);
}
