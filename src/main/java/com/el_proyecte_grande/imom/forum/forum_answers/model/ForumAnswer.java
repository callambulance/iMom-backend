package com.el_proyecte_grande.imom.forum.forum_answers.model;

import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forum_answers")
public class ForumAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "forum_answer_id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private ForumQuestion forumQuestion;

    @ApiModelProperty(value = "Answer as String")
    private String answer;

    @ApiModelProperty(value = "Date of submission")
    private LocalDate date;

//    private int value;
//    private String attachment;
}
