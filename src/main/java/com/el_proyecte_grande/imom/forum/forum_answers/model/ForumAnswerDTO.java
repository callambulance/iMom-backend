package com.el_proyecte_grande.imom.forum.forum_answers.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ForumAnswerDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "Answer as String")
    private String answer;

    @ApiModelProperty(value = "Date of submission")
    private LocalDate date;

    private Integer likesQuantity;

}
