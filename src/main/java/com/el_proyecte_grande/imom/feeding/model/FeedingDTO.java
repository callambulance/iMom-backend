package com.el_proyecte_grande.imom.feeding.model;

import lombok.Data;

@Data
public class FeedingDTO {

    private Long id;
    private int date;
    private String feedingType;
    private int time;
    private int quantity;
}
