package com.el_proyecte_grande.imom.users.model;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.kids.model.Kid;
import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfo;
//import com.el_proyecte_grande.imom.pregnancy_status.model.PregnancyStatus;
import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirth;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirth;
import com.el_proyecte_grande.imom.user_diary.model.Diary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    private Long id;

    private String googleId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Kid> kids;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PregnancyInfo pregnancyInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ForumQuestion> forumQuestionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ForumAnswer> forumAnswerList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Diary> diaryList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TaskBeforeBirth> taskBeforeBirthList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TaskAfterBirth> taskAfterBirthList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Feeding> feedingList;

    @ApiModelProperty(value = "User's first name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "User's age")
    private Integer age;
    @ApiModelProperty(value = "User's e-mail")
    private String email;

    @ApiModelProperty(value = "User's password")
    private String password;


    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

}
