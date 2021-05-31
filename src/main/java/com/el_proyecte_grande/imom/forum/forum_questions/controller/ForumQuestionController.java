package com.el_proyecte_grande.imom.forum.forum_questions.controller;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswerDTO;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestionDTO;
import com.el_proyecte_grande.imom.forum.forum_questions.service.ForumQuestionService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ForumQuestionController {
    private final ForumQuestionService forumQuestionService;

    public ForumQuestionController(ForumQuestionService forumQuestionService) {
        this.forumQuestionService = forumQuestionService;
    }

    @CrossOrigin
    @ApiOperation("Operation to list all forum questions by given question ID")
    @GetMapping("/users/{userId}/forum-questions")
    public List<ForumQuestionDTO> showForumQuestions(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return forumQuestionService.findAllByUserId(userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to save new forum question to given user ID")
    @PostMapping("/forum-questions/add_question")
    public ForumQuestionDTO saveForumQuestion(@RequestParam Long userId, @RequestBody ForumQuestionDTO forumQuestionDTO) {
        System.out.println("request");
        return forumQuestionService.saveForumQuestion(userId, forumQuestionDTO);
    }

    @CrossOrigin
    @GetMapping("/forum-questions/{questionId}")
    public ForumQuestionDTO getOneQuestion(@PathVariable("questionId") Long questionId) {
        return forumQuestionService.getOneQuestionWithAnswers(questionId);
    }

    @CrossOrigin
    @DeleteMapping("/forum-questions/delete/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        forumQuestionService.deleteQuestion(questionId);
    }

    @CrossOrigin
    @PostMapping("/forum-questions/{questionId}/update")
    public void updateForumQuestion(@PathVariable("questionId") Long questionId, @RequestBody ForumQuestionDTO forumQuestionDTO) {
        forumQuestionService.updateForumQuestion(questionId, forumQuestionDTO);
    }

    @CrossOrigin
    @GetMapping("/forum-questions")
    public List<ForumQuestionDTO> findAll() {
        return forumQuestionService.findAllByOrderByDateAsc();
    }


//    make it sorted by latest
    @CrossOrigin
    @ApiOperation("Operation to list all forum question and order them by date")
    @GetMapping("/forum-questions/last-added")
    public List<ForumQuestionDTO> findLastAddedQuestions() {
        return forumQuestionService.findAllByOrderByDateAsc();
    }

}
