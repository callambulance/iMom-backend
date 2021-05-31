package com.el_proyecte_grande.imom.pregnancy_info.model;

import com.el_proyecte_grande.imom.users.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pregnancy_info")
public class PregnancyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pregnancy_info_id")
    @ApiModelProperty(value = "Identifier")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregnancyInfo")
    private List<WeightDuringPregnancy> weightDuringPregnancy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregnancyInfo")
    private List<Contraction> contraction;

    @ApiModelProperty(value = "Number of children")
    private int numberOfChildren;

    @ApiModelProperty(value = "Expected due date")
    private int dueDate;

    @ApiModelProperty(value = "Number of kicks")
    private int kicksCount;

    @ApiModelProperty(value = "Status of pregnancy")
    private boolean status;


}
