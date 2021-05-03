package com.el_proyecte_grande.imom.kids.service;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import com.el_proyecte_grande.imom.feeding.repository.FeedingRepository;
import com.el_proyecte_grande.imom.kids.model.Kid;
import com.el_proyecte_grande.imom.kids.model.KidDTO;
import com.el_proyecte_grande.imom.kids.repository.KidRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KidService {
    private final KidRepository kidRepository;
    private final UserRepository userRepository;
    private final FeedingRepository feedingRepository;

    public KidService(KidRepository kidRepository, UserRepository userRepository, FeedingRepository feedingRepository) {
        this.kidRepository = kidRepository;
        this.userRepository = userRepository;
        this.feedingRepository = feedingRepository;
    }

    public List<KidDTO> findAllByUserId(Long userId) {
        return convertToList(kidRepository.findAllByParentId(userId));
    }

    public KidDTO saveKid(Long userId, KidDTO kidDTO){
        User user = userRepository.findById(userId).orElseThrow();
        List<Feeding> feedingList = feedingRepository.findAllByUserIdAndKidId(userId, kidDTO.getId());
        Kid kid = convert(kidDTO, user, feedingList);
        return convert(kidRepository.save(kid));
    }

    private Kid convert(KidDTO kidDTO, User user, List<Feeding> feedingList) {
        Kid kid = new Kid();
        kid.setFeeding(feedingList);
        kid.setParent(user);
        kid.setHeight(kidDTO.getHeight());
        kid.setBirthDate(kidDTO.getBirthDate());
        kid.setName(kidDTO.getName());
        kid.setLastName(kidDTO.getLastName());
        kid.setSex(kidDTO.getSex());
        kid.setWeight(kidDTO.getWeight());
        return kid;
    }

    private KidDTO convert(Kid kid) {
        KidDTO kidDTO = new KidDTO();
        kidDTO.setId(kid.getId());
        kidDTO.setBirthDate(kid.getBirthDate());
        kidDTO.setHeight(kid.getHeight());
        kidDTO.setSex(kid.getSex());
        kidDTO.setName(kid.getName());
        kidDTO.setWeight(kid.getWeight());
        kidDTO.setLastName(kid.getLastName());
        return kidDTO;
    }

    private List<KidDTO> convertToList(List<Kid> kidList) {
        ModelMapper modelMapper = new ModelMapper();

        return kidList
                .stream()
                .map(kid -> modelMapper.map(kid, KidDTO.class))
                .collect(Collectors.toList());
    }

    public List<Kid> findAll() {
        return kidRepository.findAll();
    }
}
