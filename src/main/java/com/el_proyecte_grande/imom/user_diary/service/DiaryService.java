package com.el_proyecte_grande.imom.user_diary.service;

import com.el_proyecte_grande.imom.forum.forum_questions.model.ForumQuestion;
import com.el_proyecte_grande.imom.user_diary.model.Diary;
import com.el_proyecte_grande.imom.user_diary.model.DiaryDTO;
import com.el_proyecte_grande.imom.user_diary.repository.DiaryRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public DiaryService(DiaryRepository diaryRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
    }


    public List<DiaryDTO> findAllByUserID(Long userId) {
        return convertToList(diaryRepository.findAllByUserId(userId));
    }

    public DiaryDTO saveDiary(Long userId, DiaryDTO diaryDTO){
        User user = userRepository.findById(userId).orElseThrow();
        Diary diary = convert(diaryDTO, user);
        return convert(diaryRepository.save(diary));
    }

    private Diary convert(DiaryDTO diaryDTO, User user) {
        Diary diary = new Diary();
        diary.setDescription(diaryDTO.getDescription());
        diary.setUser(user);
        return diary;
    }

    private DiaryDTO convert(Diary diary) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setId(diary.getId());
        diaryDTO.setDescription(diary.getDescription());
        return diaryDTO;
    }

    private List<DiaryDTO> convertToList(List<Diary> diaryList) {
        ModelMapper modelMapper = new ModelMapper();

        return diaryList
                .stream()
                .map(diary -> modelMapper.map(diary, DiaryDTO.class))
                .collect(Collectors.toList());
    }


    public void deletePost(Long postId) {
        diaryRepository.deleteById(postId);
    }

    public void updateTask(Long postId, DiaryDTO diaryDTO) {
        Diary diary = diaryRepository.findById(postId).get();
        diary.setDescription(diaryDTO.getDescription());
        diary.setDate(diaryDTO.getDate());

        diaryRepository.save(diary);

    }

    public DiaryDTO findOnePostByID(Long postId) {
        return convert(diaryRepository.findById(postId).get());
    }
}
