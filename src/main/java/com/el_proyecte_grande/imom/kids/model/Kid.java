package com.el_proyecte_grande.imom.kids.model;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import com.el_proyecte_grande.imom.users.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "kids")
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kid_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kid")
    private List<Feeding> feeding;

    @Column(name = "first_name")
    private String name;
    private String lastName;
    private String sex;

    private int birthDate;
    private int weight;
    private int height;


}
