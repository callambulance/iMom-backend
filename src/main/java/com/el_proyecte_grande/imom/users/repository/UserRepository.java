package com.el_proyecte_grande.imom.users.repository;

import com.el_proyecte_grande.imom.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByGoogleId(String id);
    Optional<User> findByUsername(String username);

    User findByEmail(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
