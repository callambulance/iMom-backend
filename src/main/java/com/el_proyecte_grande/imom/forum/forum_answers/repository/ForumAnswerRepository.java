package com.el_proyecte_grande.imom.forum.forum_answers.repository;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumAnswerRepository extends JpaRepository<ForumAnswer, Long> {

    List<ForumAnswer> findAllByForumQuestionId(Long questionId);
    List<ForumAnswer> findAllByUserId(Long userId);
}