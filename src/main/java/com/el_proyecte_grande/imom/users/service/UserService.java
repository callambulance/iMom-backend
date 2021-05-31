package com.el_proyecte_grande.imom.users.service;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import com.el_proyecte_grande.imom.feeding.repository.FeedingRepository;
import com.el_proyecte_grande.imom.forum.forum_answers.model.ForumAnswer;
import com.el_proyecte_grande.imom.forum.forum_answers.repository.ForumAnswerRepository;
import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.forum.forum_questions.repository.ForumQuestionRepository;
import com.el_proyecte_grande.imom.kids.model.Kid;
import com.el_proyecte_grande.imom.kids.repository.KidRepository;
import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfo;
import com.el_proyecte_grande.imom.pregnancy_info.repository.PregnancyInfoRepository;
import com.el_proyecte_grande.imom.tasks_after_birth.model.TaskAfterBirth;
import com.el_proyecte_grande.imom.tasks_after_birth.repository.TaskAfterBirthRepository;
import com.el_proyecte_grande.imom.tasks_before_birth.model.TaskBeforeBirth;
import com.el_proyecte_grande.imom.tasks_before_birth.repository.TaskBeforeBirthRepository;
import com.el_proyecte_grande.imom.user_diary.model.Diary;
import com.el_proyecte_grande.imom.user_diary.repository.DiaryRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.model.UserDTO;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KidRepository kidRepository;
    private final PregnancyInfoRepository pregnancyInfoRepository;
    private final ForumQuestionRepository forumQuestionRepository;
    private final ForumAnswerRepository forumAnswerRepository;
    private final DiaryRepository diaryRepository;
    private final TaskBeforeBirthRepository taskBeforeBirthRepository;
    private final TaskAfterBirthRepository taskAfterBirthRepository;
    private final FeedingRepository feedingRepository;

    public UserService(UserRepository userRepository, KidRepository kidRepository, PregnancyInfoRepository pregnancyInfoRepository, ForumQuestionRepository forumQuestionRepository, ForumAnswerRepository forumAnswerRepository, DiaryRepository diaryRepository, TaskBeforeBirthRepository taskBeforeBirthRepository, TaskAfterBirthRepository taskAfterBirthRepository, FeedingRepository feedingRepository) {
        this.userRepository = userRepository;
        this.kidRepository = kidRepository;
        this.pregnancyInfoRepository = pregnancyInfoRepository;
        this.forumQuestionRepository = forumQuestionRepository;
        this.forumAnswerRepository = forumAnswerRepository;
        this.diaryRepository = diaryRepository;
        this.taskBeforeBirthRepository = taskBeforeBirthRepository;
        this.taskAfterBirthRepository = taskAfterBirthRepository;
        this.feedingRepository = feedingRepository;
    }

    public UserDTO findByUserId(Long id) {
        return convert(userRepository.findById(id).get());
    }

    public UserDTO saveUser(Long userId, UserDTO userDTO) {
        List<Kid> kidList = kidRepository.findAllByParentId(userId);
        PregnancyInfo pregnancyInfo = pregnancyInfoRepository.findByUserId(userId);
        List<ForumQuestion> forumQuestionList = forumQuestionRepository.findAllByUserId(userId);
        List<ForumAnswer> forumAnswerList = forumAnswerRepository.findAllByUserId(userId);
        List<Diary> diaryList = diaryRepository.findAllByUserId(userId);
        List<TaskBeforeBirth> taskBeforeBirthList = taskBeforeBirthRepository.findAllByUserId(userId);
        List<TaskAfterBirth> taskAfterBirthList = taskAfterBirthRepository.findAllByUserId(userId);
        List<Feeding> feedingList = feedingRepository.findAllByUserId(userId);
        User user = convert(userDTO, kidList, pregnancyInfo, forumQuestionList, forumAnswerList, diaryList, taskBeforeBirthList, taskAfterBirthList, feedingList);
        return convert(userRepository.save(user));
    }

    public User convert(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        return user;
    }

    public User convert(UserDTO userDTO, List<Kid> kidList, PregnancyInfo pregnancyInfo,
                        List<ForumQuestion> forumQuestionList, List<ForumAnswer> forumAnswerList, List<Diary> diaryList,
                        List<TaskBeforeBirth> taskBeforeBirthList, List<TaskAfterBirth> taskAfterBirthList,
                        List<Feeding> feedingList) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setKids(kidList);
        user.setPregnancyInfo(pregnancyInfo);
        user.setForumQuestionList(forumQuestionList);
        user.setForumAnswerList(forumAnswerList);
        user.setDiaryList(diaryList);
        user.setTaskBeforeBirthList(taskBeforeBirthList);
        user.setTaskAfterBirthList(taskAfterBirthList);
        user.setFeedingList(feedingList);
        return user;
    }

    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Transactional()
    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }
}
