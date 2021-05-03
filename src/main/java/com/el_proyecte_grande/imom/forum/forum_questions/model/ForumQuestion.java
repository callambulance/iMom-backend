package com.el_proyecte_grande.imom.forum.forum_questions.model;

import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forum_questions")
public class ForumQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "forum_question_id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "forumQuestion")
    private List<ForumAnswer> forumAnswer;

    @ApiModelProperty(value = "Question's title")
    private String questionTitle;

    @ApiModelProperty(value = "Question as String")
    private String question;

    @ApiModelProperty(value = "Question's category")
    private String category;

    @ApiModelProperty(value = "Date of submission")
    private LocalDate date;

//    private int value;
//    private String attachment;
}
