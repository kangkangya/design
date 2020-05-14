package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.GoWith;
import com.panzhihua.travel.entity.HotGoWith;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoWithControllerTest {
    @Autowired
    private GoWithController goWithController;

    @Autowired
    private HotGoWithController hotGoWithController;

    @Test
    public void testSave() {
        List<HotGoWith> list = hotGoWithController.list();

        list.forEach(l -> {
            build(l);
        });
    }

    private void build(HotGoWith hotGoWith) {
        IntStream.range(0, 20).forEach(i -> {
            GoWith goWith = new GoWith();
            goWith.setId(IDUtil.id());
            goWith.setHotGoWithId(hotGoWith.getId());
//            goWith.setUserId("5a950ab8883a46e58a1c39969adeceb9");
//            goWith.setTarget(hotGoWith.getTarget());
            goWith.setTitle("说走就走" + i);
//            goWith.setSummary("说走就走" + i);
            goWith.setPicUrl("spoit-" + i + ".jpeg");
            goWith.setAttentionNum(i);
            goWith.setTravelTime(buildTravelTime());
            goWith.setTimestamp(System.currentTimeMillis());
            try {
                goWithController.save(goWith);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Long buildTravelTime() {
        long l = System.currentTimeMillis();
        int oneDay = 86400000;
        return l + (new Random().nextInt(30 - 1) + 1 + 1) * oneDay;
    }
}
