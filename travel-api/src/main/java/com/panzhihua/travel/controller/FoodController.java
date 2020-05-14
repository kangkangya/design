package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.FoodQueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Food;
import com.panzhihua.travel.service.FoodService;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/food")
@AllArgsConstructor
public class FoodController {
    private FoodService foodService;

    @GetMapping("findById")
    public RetDto findById(String id){
        return foodService.findById(id);
    }

    @GetMapping("list")
    public RetDto list(FoodQueryCondition foodQueryCondition) {
        return foodService.find(foodQueryCondition);
    }

    @DeleteMapping("delete")
    public void delete( String id) {
        foodService.delete(id);
    }

    @PostMapping("save")
    public RetDto save(@RequestBody Food food) {
        food.setId(IDUtil.id());
        food.setTimestamp(System.currentTimeMillis());
        return foodService.save(food);
    }

    @PostMapping("edit")
    public RetDto edit(@RequestBody Food food) {
        return foodService.save(food);
    }
}
