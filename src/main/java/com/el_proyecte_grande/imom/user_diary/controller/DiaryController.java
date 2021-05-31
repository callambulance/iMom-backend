package com.el_proyecte_grande.imom.user_diary.controller;

import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirthDTO;
import com.el_proyecte_grande.imom.user_diary.model.Diary;
import com.el_proyecte_grande.imom.user_diary.model.DiaryDTO;
import com.el_proyecte_grande.imom.user_diary.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @CrossOrigin
    @ApiOperation("Operation to list all diaries by given user ID")
    @GetMapping("/diary/{userId}")
    public List<DiaryDTO> showDiary(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return diaryService.findAllByUserID(userId);
    }

    @CrossOrigin
    @GetMapping("/diaries/{postId}")
    public DiaryDTO getOneDiaryPost(@PathVariable("postId") Long postId) {
        log.info("Log Message: ");
        return diaryService.findOnePostByID(postId);
    }


    @CrossOrigin
    @PostMapping("/diaries")
    @ApiOperation("Operation to save new diary for given user ID")
    public DiaryDTO saveDiary(@RequestParam Long userId, @RequestBody DiaryDTO diaryDTO) {
        return diaryService.saveDiary(userId, diaryDTO);
    }
//
//    @CrossOrigin
//    @GetMapping("/diaries")
//    public List<Diary> findAll() {
//        return diaryService.findAll();
//    }

    @CrossOrigin
    @DeleteMapping("/dairy/delete/{postId}")
    public void deleteDairyPost(@PathVariable("postId") Long postId) {
        diaryService.deletePost(postId);
    }

    @CrossOrigin
    @PostMapping("/dairy/{taskId}/update")
    public void updateDairyPost(@PathVariable("taskId") Long postId, @RequestBody DiaryDTO diaryDTO) {
        diaryService.updateTask(postId, diaryDTO);
    }

}
