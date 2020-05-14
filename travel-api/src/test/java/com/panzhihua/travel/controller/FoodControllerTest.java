package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Food;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodControllerTest {
    @Autowired
    private FoodController foodController;

    @Test
    public void testSave() {
        IntStream.range(0, 20).forEach(i -> buildFoodInfo(i));
    }

    private void buildFoodInfo(int i) {
        Food food = new Food();
        food.setId(IDUtil.id());
        food.setFoodCode(buildFoodCode());
        food.setCommentNum(i);
        food.setAddress(buildAddress());
        food.setTitle(buildTitle());
        food.setScore(buildScore());
        food.setPicUrl("food-" + i + ".jpeg");
        food.setRegionCode(buildRegionCode());
        food.setSummary(buildSummary());
        food.setNewComment(buildNewComment());
        food.setTimestamp(System.currentTimeMillis());
        foodController.save(food);
    }

    private String buildAddress() {
        String[] titles = {"机场路10号", "中心广场1号", "沃尔玛", "回复新路", "大马路"};
        return titles[new Random().nextInt(titles.length)];
    }

    private String buildTitle() {
        String[] titles = {"水煮鱼", "锅包肉", "京酱肉丝", "香菇青菜", "火锅"};
        return titles[new Random().nextInt(titles.length)];
    }

    private String buildSummary() {
        String[] titles = {"哈哈哈哈哈", "好吃好吃好吃好吃好吃", "不错不错不错不错", "难吃难吃难吃难吃", "美味美味美味美味美味"};
        return titles[new Random().nextInt(titles.length)];
    }

    private String buildNewComment() {
        String[] titles = {"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                "好吃好吃好吃好吃好吃哈哈哈哈哈", "不错不错不错不错哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                "难吃难吃难吃难吃哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                "美味美味美味美味美味哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"};
        return titles[new Random().nextInt(titles.length)];
    }

    private double buildScore() {
        double d = new Random().nextDouble() * new Random().nextInt(5);
        if (d == 0) {
            d += 1;
        }
        DecimalFormat df = new DecimalFormat("#.0");
        return Double.valueOf(df.format(d));
    }

    private String buildRegionCode() {
        String[] codes = {"00000", "10000", "20000", "30000", "40000"};
        return codes[new Random().nextInt(codes.length)];
    }

    private String buildFoodCode() {
        String[] codes = {"00000", "10000", "20000", "30000", "40000", "50000"};
        return codes[new Random().nextInt(codes.length)];
    }
}
