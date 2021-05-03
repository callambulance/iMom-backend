package com.el_proyecte_grande.imom.users.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {

    @ApiModelProperty(value = "Identifier")
    private Long id;

    @ApiModelProperty(value = "User's first name")
    private String name;

    @ApiModelProperty(value = "User's e-mail")
    private String email;
}
