package com.el_proyecte_grande.imom.users.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "User's first name")
    private String name;

    @ApiModelProperty(value = "User's first name")
    private String username;

    @ApiModelProperty(value = "User's role name")
    private Set<Role> roles = new HashSet<>();

    @ApiModelProperty(value = "User's e-mail")
    private String email;
}
