package com.el_proyecte_grande.imom.forum.forum_answers.service;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswerDTO;
import com.el_proyecte_grande.imom.forum.forum_answers.repository.ForumAnswerRepository;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.repository.ForumQuestionRepository;
import com.el_proyecte_grande.imom.likes.answer_likes.service.AnswerLikesService;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForumAnswerService {
    private final ForumAnswerRepository forumAnswerRepository;
    private final UserRepository userRepository;
    private final ForumQuestionRepository forumQuestionRepository;
    private final AnswerLikesService answerLikesService;

    public ForumAnswerService(ForumAnswerRepository forumAnswerRepository, UserRepository userRepository, ForumQuestionRepository forumQuestionRepository, AnswerLikesService answerLikesService) {
        this.forumAnswerRepository = forumAnswerRepository;
        this.userRepository = userRepository;
        this.forumQuestionRepository = forumQuestionRepository;
        this.answerLikesService = answerLikesService;
    }

    public List<ForumAnswerDTO> findAllByForumQuestionId(Long questionId) {
        return convertToList(forumAnswerRepository.findAllByForumQuestionId(questionId));
    }

    public ForumAnswerDTO saveForumAnswer(Long userId, Long questionId, ForumAnswerDTO answerDTO){
        User user = userRepository.findById(userId).orElseThrow();
        ForumQuestion question = forumQuestionRepository.findById(questionId).orElseThrow();
        ForumAnswer answer = convert(answerDTO, user, question);
        return convert(forumAnswerRepository.save(answer));
    }

    private ForumAnswer convert(ForumAnswerDTO forumAnswerDTO, User user, ForumQuestion forumQuestion) {
        ForumAnswer forumAnswer = new ForumAnswer();
        forumAnswer.setAnswer(forumAnswerDTO.getAnswer());
        forumAnswer.setUser(user);
        forumAnswer.setForumQuestion(forumQuestion);
        forumAnswer.setDate(forumAnswer.getDate());
        return forumAnswer;
    }

    private ForumAnswerDTO convert(ForumAnswer forumAnswer) {
        ForumAnswerDTO forumAnswerDTO = new ForumAnswerDTO();
        forumAnswerDTO.setId(forumAnswer.getId());
        forumAnswerDTO.setAnswer(forumAnswer.getAnswer());
        forumAnswerDTO.setDate(forumAnswer.getDate());
        forumAnswerDTO.setLikesQuantity(answerLikesService.findAllByAnswerId(forumAnswer.getId()));
        return forumAnswerDTO;
    }

    private List<ForumAnswerDTO> convertToList(List<ForumAnswer> forumAnswerList) {
        List<ForumAnswerDTO> forumAnswerDTOList = new ArrayList<>();
        for(ForumAnswer a : forumAnswerList){
            ForumAnswerDTO forumAnswerDTO = new ForumAnswerDTO();
            forumAnswerDTO.setId(a.getId());
            forumAnswerDTO.setAnswer(a.getAnswer());
            forumAnswerDTO.setDate(a.getDate());
            forumAnswerDTO.setLikesQuantity(answerLikesService.findAllByAnswerId(a.getId()));
            forumAnswerDTOList.add(forumAnswerDTO);
        }
        return forumAnswerDTOList;
//        ModelMapper modelMapper = new ModelMapper();
//
//        return forumAnswerList
//                .stream()
//                .map(answer -> modelMapper.map(answer, ForumAnswerDTO.class))
//                .collect(Collectors.toList());
    }

    public List<ForumAnswer> findAll() {
        return forumAnswerRepository.findAll();
    }

    public void deleteAnswer(Long answerId) {
        forumAnswerRepository.deleteById(answerId);
    }

    public void updateForumAnswer(Long answerId, ForumAnswerDTO forumAnswerDTO) {
        ForumAnswer forumAnswer = forumAnswerRepository.findById(answerId).get();
        forumAnswer.setAnswer(forumAnswerDTO.getAnswer());
        forumAnswer.setDate(forumAnswerDTO.getDate());

        forumAnswerRepository.save(forumAnswer);
    }
}
