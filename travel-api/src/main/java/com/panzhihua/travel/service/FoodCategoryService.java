package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.FoodCategoryRepository;
import com.panzhihua.travel.entity.FoodCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategory save(FoodCategory foodCategory) {
        return foodCategoryRepository.save(foodCategory);
    }

    public void delete(String id) {
        foodCategoryRepository.deleteById(id);
    }

    public List<FoodCategory> list() {
        return foodCategoryRepository.findAll();
    }
}
