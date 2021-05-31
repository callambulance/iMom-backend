package com.el_proyecte_grande.imom.likes.answer_likes.service;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.repository.ForumAnswerRepository;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestionDTO;
import com.el_proyecte_grande.imom.likes.answer_likes.model.AnswerLike;
import com.el_proyecte_grande.imom.likes.answer_likes.model.AnswerLikeDto;
import com.el_proyecte_grande.imom.likes.answer_likes.repository.AnswerLikesRepository;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLike;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class AnswerLikesService {
    private final AnswerLikesRepository answerLikesRepository;
    private final UserRepository userRepository;
    private final ForumAnswerRepository answerRepository;

    public AnswerLikesService(AnswerLikesRepository answerLikesRepository, UserRepository userRepository, ForumAnswerRepository answerRepository) {
        this.answerLikesRepository = answerLikesRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }


    public Integer findAllByAnswerId(Long answerId) {
        Integer likes =  answerLikesRepository.countAllByValueIsTrueAndForumAnswerId(answerId)
                - answerLikesRepository.countAllByValueIsFalseAndForumAnswerId(answerId);
        System.out.println(likes);
        return likes;
    }

    public AnswerLikeDto checkUserLike(Long answerId, Long userId){
        AnswerLikeDto likes = convert(answerLikesRepository.findByForumAnswerIdAndUserId(answerId, userId));
        return likes;
    }

    public AnswerLikeDto convert(AnswerLike answerLike){
        AnswerLikeDto answerLikeDto = new AnswerLikeDto();
        answerLikeDto.setAnswerId(answerLike.getForumAnswer().getId());
        answerLikeDto.setId(answerLike.getId());
        answerLikeDto.setUserId(answerLike.getUser().getId());
        answerLikeDto.setValue(answerLike.isValue());
        return answerLikeDto;
    }

    public Integer addingLikeOrDislike(Long answerId, Long userId, boolean value){
        Optional<User> user = userRepository.findById(userId);
        Optional<ForumAnswer> answer = answerRepository.findById(answerId);
        AnswerLike answerLike = new AnswerLike();
        answerLike.setForumAnswer(answer.get());
        answerLike.setUser(user.get());
        answerLike.setValue(value);
        answerLikesRepository.save(answerLike);
        return findAllByAnswerId(answerId);
    }

    public Integer deleteLike(Long answerId, Long userId){
        answerLikesRepository.deleteAnswerLikeByForumAnswerIdAndUserId(answerId, userId);
        return findAllByAnswerId(answerId);
    }


    private List<AnswerLike> convertToList(List<ForumQuestion> forumQuestionList) {
        ModelMapper modelMapper = new ModelMapper();
        return null;
//        return forumQuestionList
//                .stream()
//                .map(question -> modelMapper.map(question, ForumQuestionDTO.class))
//                .collect(Collectors.toList());
    }
}
