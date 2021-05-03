package com.el_proyecte_grande.imom.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleUser {
    private String sub;
    private String picture;
    private String name;
    private String email;
}