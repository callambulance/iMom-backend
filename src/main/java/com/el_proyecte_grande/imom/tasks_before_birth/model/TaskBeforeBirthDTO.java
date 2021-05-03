package com.el_proyecte_grande.imom.tasks_before_birth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskBeforeBirthDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "Status of a task")
    private Boolean taskStatus;

    @ApiModelProperty(value = "Tasks's name")
    private String taskName;

    @ApiModelProperty(value = "Task as string")
    private String taskText;
}
