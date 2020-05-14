package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Region;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionControllerTest {
    @Autowired
    private RegionController regionController;

    @Test
    public void testSave() {
        Region region1 = new Region();
        region1.setId(IDUtil.id());
        region1.setCode("00000");
        region1.setName("仁和区");
        regionController.save(region1);

        Region region2 = new Region();
        region2.setId(IDUtil.id());
        region2.setCode("10000");
        region2.setName("东区");
        regionController.save(region2);

        Region region3 = new Region();
        region3.setId(IDUtil.id());
        region3.setCode("20000");
        region3.setName("西区");
        regionController.save(region3);

        Region region4 = new Region();
        region4.setId(IDUtil.id());
        region4.setCode("30000");
        region4.setName("米易县");
        regionController.save(region4);

        Region region5 = new Region();
        region5.setId(IDUtil.id());
        region5.setCode("40000");
        region5.setName("盐边县");
        regionController.save(region5);
    }
}
