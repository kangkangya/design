package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.HotGoWith;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotGoWithControllerTest {
    @Autowired
    private HotGoWithController hotGoWithController;

    @Test
    public void testSave() {
        IntStream.range(0, 8).forEach(i -> build(i));
    }

    private void build(int i) {
        HotGoWith hotGoWith = new HotGoWith();
        hotGoWith.setId(IDUtil.id());
        hotGoWith.setTarget("西藏");
        hotGoWith.setRegionCode(buildRegionCode());
        hotGoWith.setPicUrl("hotel-" + i + ".jpeg");
        hotGoWith.setGoWithNum(i);
        hotGoWith.setAttentionNum(i);
        hotGoWith.setEnterNum(i);
        hotGoWith.setTimestamp(System.currentTimeMillis());
        hotGoWithController.save(hotGoWith);
    }

    private String buildRegionCode() {
        String[] codes = {"00000", "10000", "20000", "30000", "40000"};
        return codes[new Random().nextInt(codes.length)];
    }
}
