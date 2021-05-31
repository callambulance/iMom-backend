package com.el_proyecte_grande.imom.tasks_before_birth.controller;

import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestionDTO;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirth;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirthDTO;
import com.el_proyecte_grande.imom.tasks_before_birth.service.TaskBeforeBirthService;
import com.el_proyecte_grande.imom.user_diary.model.DiaryDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TaskBeforeBirthController {
    private final TaskBeforeBirthService taskBeforeBirthService;

    public TaskBeforeBirthController(TaskBeforeBirthService taskBeforeBirthService) {
        this.taskBeforeBirthService = taskBeforeBirthService;
    }

    @CrossOrigin
    @ApiOperation("Operation to list all tasksBeforeBirth by given user ID")
    @GetMapping("/users/{userId}/tasks-before-birth")
    public List<TaskBeforeBirthDTO> showTasksBeforeBirth(@PathVariable("userId") Long userId) {
        log.info("Log Message: ");
        return taskBeforeBirthService.findAllByUserID(userId);
    }

    @CrossOrigin
    @ApiOperation("Operation to save new taskBeforeBirth for given user ID")
    @PostMapping("/users/{userId}/tasks-before-birth")
    public TaskBeforeBirthDTO saveTaskBeforeBirth(@PathVariable("userId") Long userId, @RequestBody TaskBeforeBirthDTO taskBeforeBirthDTO) {
        return taskBeforeBirthService.saveTaskBeforeBirth(userId, taskBeforeBirthDTO);
    }

    @CrossOrigin
    @GetMapping("/tasks-before-birth")
    public List<TaskBeforeBirth> findAll() {
        return taskBeforeBirthService.findAll();
    }

    @CrossOrigin
    @GetMapping("/tasks-before-birth/{taskId}")
    public TaskBeforeBirthDTO getOneTask(@PathVariable("taskId") Long taskId) {
        log.info("Log Message: ");
        return taskBeforeBirthService.findOneTaskByID(taskId);
    }

    @CrossOrigin
    @DeleteMapping("/tasks-before-birth/delete/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskBeforeBirthService.deleteTask(taskId);
    }

    @CrossOrigin
    @PostMapping("/tasks-before-birth/{taskId}/update")
    public void updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskBeforeBirthDTO taskBeforeBirthDTO) {
        System.out.println(taskBeforeBirthDTO.getTaskText());
        taskBeforeBirthService.updateTask(taskId, taskBeforeBirthDTO);
    }
}
