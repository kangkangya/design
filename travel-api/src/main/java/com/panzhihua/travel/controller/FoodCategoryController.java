package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.FoodCategory;
import com.panzhihua.travel.service.FoodCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/food-category")
@AllArgsConstructor
public class FoodCategoryController {
    private FoodCategoryService foodCategoryService;

    @GetMapping("list")
    public List<FoodCategory> list() {
        return foodCategoryService.list();
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        foodCategoryService.delete(id);
    }

    @PostMapping("save")
    public FoodCategory save(FoodCategory foodCategory) {
        return foodCategoryService.save(foodCategory);
    }

    @PutMapping("edit")
    public FoodCategory edit(FoodCategory foodCategory) {
        return foodCategoryService.save(foodCategory);
    }
}
