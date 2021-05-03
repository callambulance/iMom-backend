package com.el_proyecte_grande.imom.forum.forum_answers.service;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswerDTO;
import com.el_proyecte_grande.imom.forum.forum_answers.repository.ForumAnswerRepository;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.repository.ForumQuestionRepository;
import com.el_proyecte_grande.imom.user_diary.model.Diary;
import com.el_proyecte_grande.imom.user_diary.model.DiaryDTO;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumAnswerService {
    private final ForumAnswerRepository forumAnswerRepository;
    private final UserRepository userRepository;
    private final ForumQuestionRepository forumQuestionRepository;

    public ForumAnswerService(ForumAnswerRepository forumAnswerRepository, UserRepository userRepository, ForumQuestionRepository forumQuestionRepository) {
        this.forumAnswerRepository = forumAnswerRepository;
        this.userRepository = userRepository;
        this.forumQuestionRepository = forumQuestionRepository;
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
        return forumAnswerDTO;
    }

    private List<ForumAnswerDTO> convertToList(List<ForumAnswer> forumAnswerList) {
        ModelMapper modelMapper = new ModelMapper();

        return forumAnswerList
                .stream()
                .map(answer -> modelMapper.map(answer, ForumAnswerDTO.class))
                .collect(Collectors.toList());
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
