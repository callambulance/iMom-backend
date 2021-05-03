package com.el_proyecte_grande.imom.forum.forum_questions.service;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.repository.ForumAnswerRepository;
import com.el_proyecte_grande.imom.forum.forum_answers.service.ForumAnswerService;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestionDTO;
import com.el_proyecte_grande.imom.forum.forum_questions.repository.ForumQuestionRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ForumQuestionService {
    private final ForumQuestionRepository forumQuestionRepository;
    private final UserRepository userRepository;
    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumAnswerService forumAnswerService;

    public ForumQuestionService(ForumQuestionRepository forumQuestionRepository, UserRepository userRepository, ForumAnswerRepository forumAnswerRepository, ForumAnswerService forumAnswerService) {
        this.forumQuestionRepository = forumQuestionRepository;
        this.userRepository = userRepository;
        this.forumAnswerRepository = forumAnswerRepository;
        this.forumAnswerService = forumAnswerService;
    }

    public List<ForumQuestionDTO> findAllByUserId(Long userId) {
        return convertToList(forumQuestionRepository.findAllByUserId(userId));
    }

    public ForumQuestionDTO saveForumQuestion(Long userId, ForumQuestionDTO questionDTO){
        User user = userRepository.findById(userId).orElseThrow();
        List<ForumAnswer> forumAnswerList = forumAnswerRepository.findAllByForumQuestionId(questionDTO.getId());
        ForumQuestion forumQuestion = convert(questionDTO, user, forumAnswerList);
        return convert(forumQuestionRepository.save(forumQuestion));
    }

    private ForumQuestion convert(ForumQuestionDTO forumQuestionDTO, User user, List<ForumAnswer> forumAnswerList) {
        ForumQuestion forumQuestion = new ForumQuestion();
        forumQuestion.setQuestion(forumQuestionDTO.getQuestion());
        forumQuestion.setUser(user);
        forumQuestion.setForumAnswer(forumAnswerList);
        forumQuestion.setQuestionTitle(forumQuestionDTO.getQuestionTitle());
        forumQuestion.setDate(forumQuestionDTO.getDate());
        forumQuestion.setCategory(forumQuestionDTO.getQuestion());
        return forumQuestion;
    }

    private ForumQuestionDTO convert(ForumQuestion forumQuestion) {
        ForumQuestionDTO forumQuestionDTO = new ForumQuestionDTO();
        forumQuestionDTO.setId(forumQuestion.getId());
        forumQuestionDTO.setQuestion(forumQuestionDTO.getQuestion());
        forumQuestionDTO.setDate(forumQuestion.getDate());
        forumQuestionDTO.setQuestionTitle(forumQuestion.getQuestionTitle());
        forumQuestionDTO.setCategory(forumQuestion.getCategory());
        forumQuestionDTO.setForumAnswer(forumAnswerService.findAllByForumQuestionId(forumQuestion.getId()));
        return forumQuestionDTO;
    }

    private List<ForumQuestionDTO> convertToList(List<ForumQuestion> forumQuestionList) {
        ModelMapper modelMapper = new ModelMapper();

        return forumQuestionList
                .stream()
                .map(question -> modelMapper.map(question, ForumQuestionDTO.class))
                .collect(Collectors.toList());
    }

    public List<ForumQuestionDTO> findAllByOrderByDateAsc(){
        List<ForumQuestion> allQuestions = forumQuestionRepository.findAllByOrderByDateDesc();
        return convertToList(allQuestions);
    }


    public ForumQuestionDTO getOneQuestionWithAnswers(Long questionId){
        return convert(forumQuestionRepository.findById(questionId).orElseThrow());
    }

    public void deleteQuestion(Long questionId) {
        forumQuestionRepository.deleteById(questionId);
    }

    public void updateForumQuestion(Long questionId, ForumQuestionDTO forumQuestionDTO) {
        ForumQuestion forumQuestion = forumQuestionRepository.findById(questionId).get();
        forumQuestion.setQuestion(forumQuestionDTO.getQuestion());
        forumQuestion.setDate(forumQuestionDTO.getDate());
        forumQuestion.setQuestionTitle(forumQuestionDTO.getQuestionTitle());
        forumQuestion.setCategory(forumQuestionDTO.getCategory());

        forumQuestionRepository.save(forumQuestion);
    }


}
