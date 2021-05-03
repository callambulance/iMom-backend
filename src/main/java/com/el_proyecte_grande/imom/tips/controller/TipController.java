package com.el_proyecte_grande.imom.tips.controller;

import com.el_proyecte_grande.imom.tips.model.Tip;
import com.el_proyecte_grande.imom.tips.service.TipService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TipController {
    private final TipService tipService;

    @Autowired
    public TipController(TipService tipService) {
        log.info("Log Message: ");
        this.tipService = tipService;
    }

    @CrossOrigin
    @ApiOperation("Operation to get tip by current date")
    @GetMapping("/tip")
    public Tip getTipByDate() {
        return tipService.getTipByDate();
    }
}
