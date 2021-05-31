package com.el_proyecte_grande.imom.likes.answer_likes.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AnswerLikeDto {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "ID of user liking/disliking answer")
    private Long userId;

    @ApiModelProperty(value = "ID of an answer")
    private Long answerId;

    @ApiModelProperty(value = "Value where true=like / false=dislike")
    private boolean value;


}
