package com.el_proyecte_grande.imom.forum.forum_answers.controller;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswerDTO;
import com.el_proyecte_grande.imom.forum.forum_answers.service.ForumAnswerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ForumAnswerController {
    private final ForumAnswerService forumAnswerService;

    public ForumAnswerController(ForumAnswerService forumAnswerService) {
        this.forumAnswerService = forumAnswerService;
    }

//    @CrossOrigin
//    @GetMapping("/forum-answers/{questionId}")
//    public List<ForumAnswerDTO> showForumAnswers(@PathVariable("userId") Long userId, @PathVariable("questionId") Long questionId) {
//        log.info("Log Message: ");
//        return forumAnswerService.findAllByForumQuestionId(questionId);
//    }

    @CrossOrigin
    @ApiOperation("Operation to list all forum answers by given question ID")
    @DeleteMapping("/forum-answers/delete/{answerId}")
    public void deleteAnswer(@PathVariable("answerId") Long answerId) {
        forumAnswerService.deleteAnswer(answerId);
    }

    @CrossOrigin
    @ApiOperation("Operation to save new forum answer to given question")
    @PostMapping("/forum-answers/{questionId}")
    public ForumAnswerDTO saveForumAnswer(@RequestParam Long userId, @PathVariable("questionId") Long questionId, @RequestBody ForumAnswerDTO forumAnswerDTO) {
        return forumAnswerService.saveForumAnswer(userId, questionId, forumAnswerDTO);
    }

    @CrossOrigin
    @PostMapping("/forum-answers/{answerId}/update")
    public void updateForumAnswer(@PathVariable("answerId") Long answerId, @RequestBody ForumAnswerDTO forumAnswerDTO) {
        forumAnswerService.updateForumAnswer(answerId, forumAnswerDTO);
    }

    @CrossOrigin
    @GetMapping("/forum-answers")
    public List<ForumAnswer> findAll() {
        return forumAnswerService.findAll();
    }
}
