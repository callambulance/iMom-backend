package com.el_proyecte_grande.imom.likes.answer_likes.controller;
import com.el_proyecte_grande.imom.likes.answer_likes.model.AnswerLikeDto;
import com.el_proyecte_grande.imom.likes.answer_likes.service.AnswerLikesService;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLikeDto;
import com.el_proyecte_grande.imom.likes.question_likes.service.QuestionLikesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class AnswerLikesController {
    private final AnswerLikesService service;

    public AnswerLikesController(AnswerLikesService service) {
        this.service = service;
    }

    @CrossOrigin
    @ApiOperation("Operation to get the number of likes for given answer ID")
    @GetMapping("/answer/answer-likes/{answerId}")
    public Integer getAnswerLikes(@PathVariable("answerId") Long answerId) {
        return service.findAllByAnswerId(answerId);
    }

    @CrossOrigin
    @ApiOperation("Operation to check if user liked/disliked given answer")
    @GetMapping("/answer/answer-likes/{answerId}/{userId}")
    public AnswerLikeDto checkUserLike(@PathVariable("answerId") Long answerId, @PathVariable("userId") Long userId) {
        return service.checkUserLike(answerId, userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to add like/dislike to given answer by user ID")
    @PostMapping("/answer/answer-likes/add-like/{answerId}/{userId}")
    public Integer setQuestionLikes(@PathVariable("answerId") Long answerId, @PathVariable("userId") Long userId, @RequestParam boolean value) {
        return service.addingLikeOrDislike(answerId, userId, value);
    }

    @CrossOrigin
    @ApiOperation("Operation to delete like/dislike from given answer by user ID")
    @DeleteMapping("/answer/answer-likes/delete-like/{answerId}/{userId}")
    public Integer deleteLikes(@PathVariable("answerId") Long answerId, @PathVariable("userId") Long userId) {
        return service.deleteLike(answerId, userId);
    }
}
