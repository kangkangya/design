package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Banner;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerControllerTest {
    @Autowired
    private BannerController bannerController;

    @Test
    public void testSave() {
        mockData();
    }

    private void mockData() {
        Banner banner = new Banner();
        banner.setId(IDUtil.id());
        banner.setTitle("攀枝花旅游1");
        banner.setSummary("旅游1 旅游1 旅游1");
        banner.setPicUrl("banner-1.jpg");
        bannerController.save(banner);

        Banner banner1 = new Banner();
        banner1.setId(IDUtil.id());
        banner1.setTitle("攀枝花旅游2");
        banner1.setSummary("旅游2 旅游2 旅游2");
        banner1.setPicUrl("banner-2.jpg");
        bannerController.save(banner1);
    }
}
