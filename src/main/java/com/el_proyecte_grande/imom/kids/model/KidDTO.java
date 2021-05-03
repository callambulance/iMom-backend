package com.el_proyecte_grande.imom.kids.model;

import lombok.Data;

@Data
public class KidDTO {
    private Long id;
    private String name;
    private String lastName;
    private String sex;

    private int birthDate;
    private int weight;
    private int height;
}
