package com.el_proyecte_grande.imom.food.service;

import com.el_proyecte_grande.imom.food.model.Food;
import com.el_proyecte_grande.imom.food.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food getRecipeById(Long ID){
        return foodRepository.findById(ID).orElseThrow();
    }

    public List<Food> findAll(){
        return foodRepository.findAll();
    }

    public List<Food> getFoodByMealtime(String mealTime){
        return foodRepository.findAllByMealTime(mealTime);
    }

}
