package com.el_proyecte_grande.imom.forum.forum_questions.repository;

import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumQuestionRepository extends JpaRepository<ForumQuestion, Long> {

    List<ForumQuestion> findAllByUserId(Long userId);

    List<ForumQuestion> findAllByOrderByDateDesc();

//    ForumQuestion findByUserIdAndId(Long userId, Long questionId);
}
