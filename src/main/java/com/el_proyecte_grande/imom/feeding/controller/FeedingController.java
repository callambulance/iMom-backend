package com.el_proyecte_grande.imom.feeding.controller;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import com.el_proyecte_grande.imom.feeding.model.FeedingDTO;
import com.el_proyecte_grande.imom.feeding.service.FeedingService;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FeedingController {
    private final FeedingService feedingService;

    public FeedingController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    @CrossOrigin
    @GetMapping("/users/{userId}/feeding/{kidId}")
    public List<FeedingDTO> showFeedingsForKid(@PathVariable("userId") Long userId, @PathVariable("kidId") Long kidId) {
        log.info("Log Message: ");
        return feedingService.findAllByUserIdAndKidId(userId, kidId);
    }

    @CrossOrigin
    @PostMapping("/users/{userId}/feeding/{kidId}")
    public FeedingDTO saveFeeding(@PathVariable("userId") Long userId, @PathVariable("kidId") Long kidId, @RequestBody FeedingDTO feedingDTO) {
        return feedingService.saveFeeding(userId, kidId, feedingDTO);
    }

    @CrossOrigin
    @GetMapping("/feeding")
    public List<Feeding> findAll() {
        return feedingService.findAll();
    }

}
