package com.el_proyecte_grande.imom.tasks_after_birth.model;

import lombok.Data;

@Data
public class TaskAfterBirthDTO {

    private Long id;
    private Boolean taskStatus;
    private String taskName;
    private String taskText;
}
