package com.el_proyecte_grande.imom.food.controller;

import com.el_proyecte_grande.imom.food.model.Food;
import com.el_proyecte_grande.imom.food.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @CrossOrigin
    @GetMapping("/food/{foodId}")
    public Food showFoodById(@PathVariable("foodId") Long foodId){
        return foodService.getRecipeById(foodId);
    }

    @CrossOrigin
    @GetMapping("/food/mealtime/{mealtime}")
    public List<Food> showFoodByMealtime(@PathVariable("mealtime") String mealtime){
        return foodService.getFoodByMealtime(mealtime);
    }

    @CrossOrigin
    @GetMapping("/food")
    public List<Food> getFood(){
        return foodService.findAll();
    }

}
