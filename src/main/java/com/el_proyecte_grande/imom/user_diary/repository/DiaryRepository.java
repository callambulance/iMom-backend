package com.el_proyecte_grande.imom.user_diary.repository;

import com.el_proyecte_grande.imom.user_diary.model.Diary;
import com.el_proyecte_grande.imom.user_diary.model.DiaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByUserId(Long userId);
}
