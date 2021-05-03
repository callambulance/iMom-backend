package com.el_proyecte_grande.imom.pregnancy_info.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contractions")
public class Contraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "contraction_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pregnancy_info_id")
    private PregnancyInfo pregnancyInfo;

    @ApiModelProperty(value = "Duration of contraction")
    private int duration;

    @ApiModelProperty(value = "Strength of contraction")
    private int strength;
}
