package com.el_proyecte_grande.imom.tasks_after_birth.service;

import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirth;
import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirthDTO;
import com.el_proyecte_grande.imom.tasks_after_birth.repository.TaskAfterBirthRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskAfterBirthService {
    private final TaskAfterBirthRepository taskAfterBirthRepository;
    private final UserRepository userRepository;

    public TaskAfterBirthService(TaskAfterBirthRepository taskAfterBirthRepository, UserRepository userRepository) {
        this.taskAfterBirthRepository = taskAfterBirthRepository;
        this.userRepository = userRepository;
    }

    public List<TaskAfterBirthDTO> findAllByUserID(Long userId) {
        return convertToList(taskAfterBirthRepository.findAllByUserId(userId));
    }


    public TaskAfterBirthDTO saveTaskAfterBirth(Long userId, TaskAfterBirthDTO taskAfterBirthDTO){
        User user = userRepository.findById(userId).orElseThrow();
        TaskAfterBirth taskAfterBirth = convert(taskAfterBirthDTO, user);
        return convert(taskAfterBirthRepository.save(taskAfterBirth));
    }

    private TaskAfterBirth convert(TaskAfterBirthDTO taskAfterBirthDTO, User user) {
        TaskAfterBirth taskAfterBirth = new TaskAfterBirth();
        taskAfterBirth.setTaskName(taskAfterBirthDTO.getTaskName());
        taskAfterBirth.setTaskText(taskAfterBirthDTO.getTaskText());
        taskAfterBirth.setTaskStatus(taskAfterBirthDTO.getTaskStatus());
        taskAfterBirth.setUser(user);
        return taskAfterBirth;
    }

    private TaskAfterBirthDTO convert(TaskAfterBirth taskAfterBirth) {
        TaskAfterBirthDTO taskAfterBirthDTO = new TaskAfterBirthDTO();
        taskAfterBirthDTO.setId(taskAfterBirth.getId());
        taskAfterBirthDTO.setTaskName(taskAfterBirth.getTaskName());
        taskAfterBirthDTO.setTaskText(taskAfterBirth.getTaskText());
        taskAfterBirthDTO.setTaskStatus(taskAfterBirth.getTaskStatus());
        return taskAfterBirthDTO;
    }

    private List<TaskAfterBirthDTO> convertToList(List<TaskAfterBirth> taskAfterBirthList) {
        ModelMapper modelMapper = new ModelMapper();

        return taskAfterBirthList
                .stream()
                .map(task -> modelMapper.map(task, TaskAfterBirthDTO.class))
                .collect(Collectors.toList());
    }
}
