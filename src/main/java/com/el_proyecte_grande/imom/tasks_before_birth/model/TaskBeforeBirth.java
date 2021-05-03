package com.el_proyecte_grande.imom.tasks_before_birth.model;

import com.el_proyecte_grande.imom.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks_before_birth")
public class TaskBeforeBirth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "task_before_birth_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty(value = "Status of a task")
    private Boolean taskStatus;

    @ApiModelProperty(value = "Tasks's name")
    private String taskName;

    @ApiModelProperty(value = "Task as string")
    private String taskText;
}
