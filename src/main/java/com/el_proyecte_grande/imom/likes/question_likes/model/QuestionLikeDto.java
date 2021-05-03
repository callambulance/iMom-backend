package com.el_proyecte_grande.imom.likes.question_likes.model;


import lombok.Data;

@Data
public class QuestionLikeDto {
    private Long id;
    private Long userId;
    private Long questionId;
    private boolean value;
}
