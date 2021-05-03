package com.el_proyecte_grande.imom.likes.question_likes.controller;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLike;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLikeDto;
import com.el_proyecte_grande.imom.likes.question_likes.service.QuestionLikesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class QuestionLikesController {
    private final QuestionLikesService service;

    public QuestionLikesController(QuestionLikesService service) {
        this.service = service;
    }


    @CrossOrigin
    @ApiOperation("Operation to get the number of likes for given question ID")
    @GetMapping("/question/question-likes/{questionId}")
    public Integer getQuestionLikes(@PathVariable("questionId") Long questionId) {
        return service.findAllByQuestionId(questionId);
    }

    @CrossOrigin
    @ApiOperation("Operation to check if user liked/disliked given question")
    @GetMapping("/question/question-likes/{questionId}/{userId}")
    public QuestionLikeDto checkUserLike(@PathVariable("questionId") Long questionId, @PathVariable("userId") Long userId) {
        return service.checkUserLike(questionId, userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to add like/dislike to given question by user ID")
    @PostMapping("/question/question-likes/add-like/{questionId}/{userId}")
    public Integer setQuestionLikes(@PathVariable("questionId") Long questionId, @PathVariable("userId") Long userId, @RequestParam boolean value) {
        return service.addingLikeOrDislike(questionId, userId, value);
    }

    @CrossOrigin
    @ApiOperation("Operation to delete like/dislike from given question by user ID")
    @DeleteMapping("/question/question-likes/delete-like/{questionId}/{userId}")
    public Integer deleteLikes(@PathVariable("questionId") Long questionId, @PathVariable("userId") Long userId) {
        return service.deleteLike(questionId, userId);
    }
}
