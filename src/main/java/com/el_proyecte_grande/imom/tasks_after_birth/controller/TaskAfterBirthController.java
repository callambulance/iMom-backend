package com.el_proyecte_grande.imom.tasks_after_birth.controller;

import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirth;
import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirthDTO;
import com.el_proyecte_grande.imom.tasks_after_birth.service.TaskAfterBirthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TaskAfterBirthController {
    private final TaskAfterBirthService taskAfterBirthService;

    public TaskAfterBirthController(TaskAfterBirthService taskAfterBirthService) {
        this.taskAfterBirthService = taskAfterBirthService;
    }

    @CrossOrigin
    @GetMapping("/users/{userId}/tasks-after-birth")
    public List<TaskAfterBirthDTO> showTasksAfterBirth(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return taskAfterBirthService.findAllByUserID(userId);
    }

    @CrossOrigin
    @PostMapping("/users/{userId}/tasks-after-birth")
    public TaskAfterBirthDTO saveTaskAfterBirth(@PathVariable("userId") Long userId, @RequestBody TaskAfterBirthDTO taskAfterBirthDTO) {
        return taskAfterBirthService.saveTaskAfterBirth(userId, taskAfterBirthDTO);
    }

}
