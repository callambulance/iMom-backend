package com.el_proyecte_grande.imom.tasks_before_birth.service;

import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirth;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirthDTO;
import com.el_proyecte_grande.imom.tasks_before_birth.repository.TaskBeforeBirthRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TaskBeforeBirthService {
    private final TaskBeforeBirthRepository taskBeforeBirthRepository;
    private final UserRepository userRepository;

    public TaskBeforeBirthService(TaskBeforeBirthRepository taskBeforeBirthRepository, UserRepository userRepository) {
        this.taskBeforeBirthRepository = taskBeforeBirthRepository;
        this.userRepository = userRepository;
    }

    public List<TaskBeforeBirthDTO> findAllByUserID(Long userId) {
        return convertToList(taskBeforeBirthRepository.findAllByUserId(userId));
    }


    public TaskBeforeBirthDTO saveTaskBeforeBirth(Long userId, TaskBeforeBirthDTO taskBeforeBirthDTO){
        User user = userRepository.findById(userId).orElseThrow();
        TaskBeforeBirth taskBeforeBirth = convert(taskBeforeBirthDTO, user);
        return convert(taskBeforeBirthRepository.save(taskBeforeBirth));
    }

    private TaskBeforeBirth convert(TaskBeforeBirthDTO taskBeforeBirthDTO, User user) {
        TaskBeforeBirth taskBeforeBirth = new TaskBeforeBirth();
        taskBeforeBirth.setTaskName(taskBeforeBirthDTO.getTaskName());
        taskBeforeBirth.setTaskText(taskBeforeBirthDTO.getTaskText());
        taskBeforeBirth.setTaskStatus(taskBeforeBirthDTO.getTaskStatus());
        taskBeforeBirth.setUser(user);
        return taskBeforeBirth;
    }

    private TaskBeforeBirthDTO convert(TaskBeforeBirth taskBeforeBirth) {
        TaskBeforeBirthDTO taskBeforeBirthDTO = new TaskBeforeBirthDTO();
        taskBeforeBirthDTO.setId(taskBeforeBirth.getId());
        taskBeforeBirthDTO.setTaskName(taskBeforeBirth.getTaskName());
        taskBeforeBirthDTO.setTaskText(taskBeforeBirth.getTaskText());
        taskBeforeBirthDTO.setTaskStatus(taskBeforeBirth.getTaskStatus());
        return taskBeforeBirthDTO;
    }

    private List<TaskBeforeBirthDTO> convertToList(List<TaskBeforeBirth> taskBeforeBirthList) {
        ModelMapper modelMapper = new ModelMapper();

        return taskBeforeBirthList
                .stream()
                .map(task -> modelMapper.map(task, TaskBeforeBirthDTO.class))
                .collect(Collectors.toList());
    }

    public void updateTask(Long taskId, TaskBeforeBirthDTO taskBeforeBirthDTO) {
        TaskBeforeBirth taskBeforeBirth = taskBeforeBirthRepository.findById(taskId).get();
        taskBeforeBirth.setTaskText(taskBeforeBirthDTO.getTaskText());
        taskBeforeBirth.setTaskName(taskBeforeBirthDTO.getTaskName());
        taskBeforeBirth.setTaskStatus(taskBeforeBirthDTO.getTaskStatus());
        System.out.println(taskBeforeBirth.getTaskName());
        System.out.println(taskBeforeBirth.getTaskName());

        taskBeforeBirthRepository.save(taskBeforeBirth);
    }

    public void deleteTask(Long taskId) {
        taskBeforeBirthRepository.deleteById(taskId);
    }

    public TaskBeforeBirthDTO findOneTaskByID(Long taskId) {
        return convert(taskBeforeBirthRepository.findById(taskId).get());
    }

    public List<TaskBeforeBirth> findAll() {
        return taskBeforeBirthRepository.findAll();
    }
}
