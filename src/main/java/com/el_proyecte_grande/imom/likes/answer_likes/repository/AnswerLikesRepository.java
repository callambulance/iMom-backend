package com.el_proyecte_grande.imom.likes.answer_likes.repository;

import com.el_proyecte_grande.imom.likes.answer_likes.model.AnswerLike;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerLikesRepository extends JpaRepository<AnswerLike, Long>{

        Integer countAllByValueIsFalseAndForumAnswerId(Long questionId);
        Integer countAllByValueIsTrueAndForumAnswerId(Long questionId);
        void deleteAnswerLikeByForumAnswerIdAndUserId(Long id, Long user_id);
        AnswerLike findByForumAnswerIdAndUserId(Long id, Long user_id);

}

