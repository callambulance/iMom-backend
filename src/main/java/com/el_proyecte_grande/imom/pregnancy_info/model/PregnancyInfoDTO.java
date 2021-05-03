package com.el_proyecte_grande.imom.pregnancy_info.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PregnancyInfoDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "Number of children")
    private int numberOfChildren;

    @ApiModelProperty(value = "Expected due date")
    private int dueDate;

    @ApiModelProperty(value = "Number of kicks")
    private int kicksCount;

    @ApiModelProperty(value = "Status of pregnancy")
    private boolean status;
}
