package com.el_proyecte_grande.imom.pregnancy_info.model;

import com.el_proyecte_grande.imom.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "weights_during_pregnancy")
public class WeightDuringPregnancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "weight_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pregnancy_info_id")
//    @JsonIgnore
    private PregnancyInfo pregnancyInfo;

    @ApiModelProperty(value = "Date of measurement")
    private LocalDate date;

    @ApiModelProperty(value = "Weight as integer")
    private int weight;
}
