package com.el_proyecte_grande.imom.user_diary.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "Diary as string")
    private String description;
    private LocalDate date;
}
