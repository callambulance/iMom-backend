package com.el_proyecte_grande.imom.feeding.model;

import com.el_proyecte_grande.imom.kids.model.Kid;
import com.el_proyecte_grande.imom.users.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "feedings")
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeding_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "kid_id")
    private Kid kid;

    private int date;
    private String feedingType;
    private int time;
    private int quantity;

}
