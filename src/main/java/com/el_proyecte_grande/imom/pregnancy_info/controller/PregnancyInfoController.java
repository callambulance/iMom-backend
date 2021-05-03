package com.el_proyecte_grande.imom.pregnancy_info.controller;

import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfo;
import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfoDTO;
import com.el_proyecte_grande.imom.pregnancy_info.service.PregnancyInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PregnancyInfoController {
    private final PregnancyInfoService pregnancyInfoService;

    public PregnancyInfoController(PregnancyInfoService pregnancyInfoService) {
        this.pregnancyInfoService = pregnancyInfoService;
    }

    @CrossOrigin
    @ApiOperation("Operation to get pregnancyInfo by given user ID")
    @GetMapping("/users/{userId}/pregnancy-info")
    public PregnancyInfoDTO showPregnancyInfo(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return pregnancyInfoService.findByUserId(userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to save pregnancyInfo to given user ID")
    @PostMapping("/users/{userId}/pregnancy-info")
    public PregnancyInfoDTO savePregnancyInfo(@PathVariable("userId") Long userId, @RequestBody PregnancyInfoDTO pregnancyInfoDTO) {
        return pregnancyInfoService.savePregnancyInfo(userId, pregnancyInfoDTO);
    }

    @CrossOrigin
    @ApiOperation("Operation to get number of kicks by given user ID")
    @GetMapping("/users/{userId}/kicks-count")
    public int showKicksCount(@PathVariable("userId") Long userId) {
        return pregnancyInfoService.getKicksCount(userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to increase number of kicks for given user ID")
    @PostMapping("/users/{userId}/increase-kicks")
    public PregnancyInfoDTO increaseKicksCount(@PathVariable("userId") Long userId) {
        return pregnancyInfoService.increaseKicksCount(userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to decrease number of kicks for given user ID")
    @PostMapping("/users/{userId}/decrease-kicks")
    public PregnancyInfoDTO decreaseKicksCount(@PathVariable("userId") Long userId) {
        return pregnancyInfoService.decreaseKicksCount(userId);
    }
}
