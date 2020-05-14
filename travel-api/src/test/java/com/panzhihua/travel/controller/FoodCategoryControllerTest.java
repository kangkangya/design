package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.FoodCategory;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodCategoryControllerTest {
    @Autowired
    private FoodCategoryController foodCategoryController;

    @Test
    public void testSave() {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setId(IDUtil.id());
        foodCategory.setCode("00000");
        foodCategory.setName("川菜");
        foodCategoryController.save(foodCategory);

        FoodCategory foodCategory1 = new FoodCategory();
        foodCategory1.setId(IDUtil.id());
        foodCategory1.setCode("10000");
        foodCategory1.setName("浙菜");
        foodCategoryController.save(foodCategory1);

        FoodCategory foodCategory2 = new FoodCategory();
        foodCategory2.setId(IDUtil.id());
        foodCategory2.setCode("20000");
        foodCategory2.setName("黑菜");
        foodCategoryController.save(foodCategory2);

        FoodCategory foodCategory3 = new FoodCategory();
        foodCategory3.setId(IDUtil.id());
        foodCategory3.setCode("30000");
        foodCategory3.setName("鲁菜");
        foodCategoryController.save(foodCategory3);

        FoodCategory foodCategory4 = new FoodCategory();
        foodCategory4.setId(IDUtil.id());
        foodCategory4.setCode("40000");
        foodCategory4.setName("粤菜");
        foodCategoryController.save(foodCategory4);

        FoodCategory foodCategory5 = new FoodCategory();
        foodCategory5.setId(IDUtil.id());
        foodCategory5.setCode("50000");
        foodCategory5.setName("湘菜");
        foodCategoryController.save(foodCategory5);
    }
}
