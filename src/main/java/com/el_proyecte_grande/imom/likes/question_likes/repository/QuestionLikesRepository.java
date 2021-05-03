package com.el_proyecte_grande.imom.likes.question_likes.repository;

import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionLikesRepository  extends JpaRepository<QuestionLike, Long>{

        Integer countAllByValueIsFalseAndForumQuestionId(Long questionId);
        Integer countAllByValueIsTrueAndForumQuestionId(Long questionId);
        void deleteQuestionLikeByForumQuestionIdAndUserId(Long id, Long user_id);
        QuestionLike findByForumQuestionIdAndUserId(Long id, Long user_id);
}

