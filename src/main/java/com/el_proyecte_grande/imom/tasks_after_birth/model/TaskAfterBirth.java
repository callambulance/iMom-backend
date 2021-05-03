package com.el_proyecte_grande.imom.tasks_after_birth.model;

import com.el_proyecte_grande.imom.users.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks_after_birth")
public class TaskAfterBirth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_after_birth_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean taskStatus;
    private String taskName;
    private String taskText;
}
