package com.el_proyecte_grande.imom.feeding.service;

import com.el_proyecte_grande.imom.feeding.model.Feeding;
import com.el_proyecte_grande.imom.feeding.model.FeedingDTO;
import com.el_proyecte_grande.imom.feeding.repository.FeedingRepository;
import com.el_proyecte_grande.imom.kids.model.Kid;
import com.el_proyecte_grande.imom.kids.repository.KidRepository;
import com.el_proyecte_grande.imom.users.model.User;
import com.el_proyecte_grande.imom.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedingService {
    private final FeedingRepository feedingRepository;
    private final UserRepository userRepository;
    private final KidRepository kidRepository;

    public FeedingService(FeedingRepository feedingRepository, UserRepository userRepository, KidRepository kidRepository) {
        this.feedingRepository = feedingRepository;
        this.userRepository = userRepository;
        this.kidRepository = kidRepository;
    }

    public List<FeedingDTO> findAllByUserIdAndKidId(Long userId, Long kidId) {
        return convertToList(feedingRepository.findAllByUserIdAndKidId(userId, kidId));
    }

    public FeedingDTO saveFeeding(Long userId, Long kidId, FeedingDTO feedingDTO){
        User user = userRepository.findById(userId).orElseThrow();
        Kid kid = kidRepository.findById(kidId).orElseThrow();
        Feeding feeding = convert(feedingDTO, user, kid);
        return convert(feedingRepository.save(feeding));
    }

    private Feeding convert(FeedingDTO feedingDTO, User user, Kid kid) {
        Feeding feeding = new Feeding();
        feeding.setFeedingType(feedingDTO.getFeedingType());
        feeding.setUser(user);
        feeding.setKid(kid);
        feeding.setDate(feeding.getDate());
        feeding.setQuantity(feeding.getQuantity());
        feeding.setTime(feedingDTO.getTime());
        return feeding;
    }

    private FeedingDTO convert(Feeding feeding) {
        FeedingDTO feedingDTO = new FeedingDTO();
        feedingDTO.setId(feeding.getId());
        feedingDTO.setFeedingType(feeding.getFeedingType());
        feedingDTO.setDate(feeding.getDate());
        feedingDTO.setQuantity(feeding.getQuantity());
        feeding.setTime(feeding.getTime());
        return feedingDTO;
    }

    private List<FeedingDTO> convertToList(List<Feeding> feedingList) {
        ModelMapper modelMapper = new ModelMapper();

        return feedingList
                .stream()
                .map(feeding -> modelMapper.map(feeding, FeedingDTO.class))
                .collect(Collectors.toList());
    }

    public List<Feeding> findAll() {
        return feedingRepository.findAll();
    }


}
