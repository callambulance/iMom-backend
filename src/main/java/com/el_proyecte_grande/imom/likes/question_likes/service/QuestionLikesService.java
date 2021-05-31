package com.el_proyecte_grande.imom.likes.question_likes.service;

import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.repository.ForumQuestionRepository;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLike;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLikeDto;
import com.el_proyecte_grande.imom.likes.question_likes.repository.QuestionLikesRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class QuestionLikesService {
    private final QuestionLikesRepository questionLikesRepository;
    private final UserRepository userRepository;
    private final ForumQuestionRepository questionRepository;

    public QuestionLikesService(QuestionLikesRepository questionLikesRepository, UserRepository userRepository, ForumQuestionRepository questionRepository) {
        this.questionLikesRepository = questionLikesRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Integer findAllByQuestionId(Long questionId) {
        Integer likes = questionLikesRepository.countAllByValueIsTrueAndForumQuestionId(questionId)
                - questionLikesRepository.countAllByValueIsFalseAndForumQuestionId(questionId);
        return likes;
    }


    public QuestionLikeDto checkUserLike(Long questionId, Long userId){
        QuestionLikeDto like = convert(questionLikesRepository.findByForumQuestionIdAndUserId(questionId, userId));
        System.out.println(like);
        return like;
    }

    public QuestionLikeDto convert(QuestionLike questionLike){
        QuestionLikeDto questionLikeDto = new QuestionLikeDto();
        questionLikeDto.setQuestionId(questionLike.getForumQuestion().getId());
        questionLikeDto.setId(questionLike.getId());
        questionLikeDto.setUserId(questionLike.getUser().getId());
        questionLikeDto.setValue(questionLike.isValue());
        return questionLikeDto;
    }

    public Integer addingLikeOrDislike(Long questionId, Long userId, boolean value){
        Optional<User> user = userRepository.findById(userId);
        Optional<ForumQuestion> question = questionRepository.findById(questionId);
        QuestionLike questionLike = new QuestionLike();
        questionLike.setForumQuestion(question.get());
        questionLike.setUser(user.get());
        questionLike.setValue(value);
        questionLikesRepository.save(questionLike);
        return findAllByQuestionId(questionId);
    }

    public Integer deleteLike(Long questionId, Long userId){
        questionLikesRepository.deleteQuestionLikeByForumQuestionIdAndUserId(questionId, userId);
        return findAllByQuestionId(questionId);
    }
}
