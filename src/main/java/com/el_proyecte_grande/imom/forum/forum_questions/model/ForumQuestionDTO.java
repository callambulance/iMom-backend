package com.el_proyecte_grande.imom.forum.forum_questions.model;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswerDTO;
import com.el_proyecte_grande.imom.likes.question_likes.model.QuestionLikeDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ForumQuestionDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "Question's title")
    private String questionTitle;

    @ApiModelProperty(value = "Question as String")
    private String question;

    @ApiModelProperty(value = "Question's category")
    private String category;
    private List<ForumAnswerDTO> forumAnswer;

    @ApiModelProperty(value = "Date of submission")
    private LocalDate date;

    private Integer likesQuantity;



}
