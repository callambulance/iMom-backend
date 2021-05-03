package com.el_proyecte_grande.imom.tips.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tips")
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "Tip's name")
    private String title;

    @ApiModelProperty(value = "Tip as string")
    private String text;

    @ApiModelProperty(value = "Tip's date")
    private LocalDate date;

}
