package com.el_proyecte_grande.imom.pregnancy_info.service;

import com.el_proyecte_grande.imom.pregnancy_info.model.Contraction;
import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfo;
import com.el_proyecte_grande.imom.pregnancy_info.model.PregnancyInfoDTO;
import com.el_proyecte_grande.imom.pregnancy_info.model.WeightDuringPregnancy;
import com.el_proyecte_grande.imom.pregnancy_info.repository.PregnancyInfoRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PregnancyInfoService {
    private final PregnancyInfoRepository pregnancyInfoRepository;
    private final UserRepository userRepository;

    public PregnancyInfoService(PregnancyInfoRepository pregnancyInfoRepository, UserRepository userRepository) {
        this.pregnancyInfoRepository = pregnancyInfoRepository;
        this.userRepository = userRepository;
    }

    public PregnancyInfoDTO findByUserId(Long userId) {
        return convert(pregnancyInfoRepository.findByUserId(userId));
    }

    public List<WeightDuringPregnancy> getListOfWeightsDuringPregnancy(Long userId) {
        PregnancyInfo pregnancyInfo = pregnancyInfoRepository.findByUserId(userId);
        return pregnancyInfo.getWeightDuringPregnancy();
    }

    public List<Contraction> getListOfContractions(Long userId) {
        PregnancyInfo pregnancyInfo = pregnancyInfoRepository.findByUserId(userId);
        return pregnancyInfo.getContraction();
    }

    public int getKicksCount(Long userId) {
        PregnancyInfo pregnancyInfo = pregnancyInfoRepository.findByUserId(userId);
        return pregnancyInfo.getKicksCount();
    }

    public PregnancyInfoDTO increaseKicksCount(Long userId) {
        PregnancyInfo pregnancyInfo = pregnancyInfoRepository.findByUserId(userId);
        int currentKicks = pregnancyInfo.getKicksCount();
        pregnancyInfo.setKicksCount(currentKicks += 1);
        return convert(pregnancyInfoRepository.save(pregnancyInfo));
    }

    public PregnancyInfoDTO decreaseKicksCount(Long userId) {
        PregnancyInfo pregnancyInfo = pregnancyInfoRepository.findByUserId(userId);
        int currentKicks = pregnancyInfo.getKicksCount();
        if (currentKicks > 0) {
            pregnancyInfo.setKicksCount(currentKicks -= 1);
        }
        return convert(pregnancyInfoRepository.save(pregnancyInfo));
    }

    public PregnancyInfoDTO savePregnancyInfo(Long userId, PregnancyInfoDTO pregnancyInfoDTO){
        User user = userRepository.findById(userId).orElseThrow();
        List<WeightDuringPregnancy> weightDuringPregnancyList = getListOfWeightsDuringPregnancy(userId);
        List<Contraction> contractionList = getListOfContractions(userId);

        PregnancyInfo pregnancyInfo = convert(pregnancyInfoDTO, user, weightDuringPregnancyList, contractionList);
        return convert(pregnancyInfoRepository.save(pregnancyInfo));
    }

    private PregnancyInfo convert(PregnancyInfoDTO pregnancyInfoDTO, User user, List<WeightDuringPregnancy> weightDuringPregnancyList, List<Contraction> contractionList) {
        PregnancyInfo pregnancyInfo = new PregnancyInfo();
        pregnancyInfo.setUser(user);
        pregnancyInfo.setDueDate(pregnancyInfoDTO.getDueDate());
        pregnancyInfo.setNumberOfChildren(pregnancyInfoDTO.getNumberOfChildren());
        pregnancyInfo.setWeightDuringPregnancy(weightDuringPregnancyList);
        pregnancyInfo.setContraction(contractionList);
        pregnancyInfo.setKicksCount(pregnancyInfoDTO.getKicksCount());
        pregnancyInfo.setStatus(pregnancyInfoDTO.isStatus());
        return pregnancyInfo;
    }

    private PregnancyInfoDTO convert(PregnancyInfo pregnancyInfo) {
        PregnancyInfoDTO pregnancyInfoDTO = new PregnancyInfoDTO();
        pregnancyInfoDTO.setId(pregnancyInfo.getId());
        pregnancyInfoDTO.setDueDate(pregnancyInfo.getDueDate());
        pregnancyInfoDTO.setNumberOfChildren(pregnancyInfo.getNumberOfChildren());
        pregnancyInfoDTO.setKicksCount(pregnancyInfo.getKicksCount());
        pregnancyInfoDTO.setStatus(pregnancyInfo.isStatus());
        return pregnancyInfoDTO;
    }

    private List<PregnancyInfoDTO> convertToList(List<PregnancyInfo> pregnancyInfoList) {
        ModelMapper modelMapper = new ModelMapper();

        return pregnancyInfoList
                .stream()
                .map(pregnancyInfo -> modelMapper.map(pregnancyInfo, PregnancyInfoDTO.class))
                .collect(Collectors.toList());
    }
}
