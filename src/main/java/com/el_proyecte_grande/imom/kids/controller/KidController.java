package com.el_proyecte_grande.imom.kids.controller;

import com.el_proyecte_grande.imom.kids.model.Kid;
import com.el_proyecte_grande.imom.kids.model.KidDTO;
import com.el_proyecte_grande.imom.kids.service.KidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class KidController {
    private final KidService kidService;

    @Autowired
    public KidController(KidService kidService) {
        this.kidService = kidService;
    }

    @CrossOrigin
    @GetMapping("/users/{userId}/kids")
    public List<KidDTO> showKids(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return kidService.findAllByUserId(userId);
    }

    @CrossOrigin
    @PostMapping("/users/{userId}/kids")
    public KidDTO saveKid(@PathVariable("userId") Long userId, @RequestBody KidDTO kidDTO) {
        return kidService.saveKid(userId, kidDTO);
    }

    @CrossOrigin
    @GetMapping("/kids")
    public List<Kid> findAll() {
        return kidService.findAll();
    }
}
