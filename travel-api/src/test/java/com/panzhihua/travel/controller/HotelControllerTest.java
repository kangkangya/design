package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Hotel;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelControllerTest {
    @Autowired
    private HotelController hotelController;

    @Test
    public void testSave() {
        IntStream.range(0, 20).forEach(i -> mockData(i));
    }

    private void mockData(int i) {
        Hotel hotel = new Hotel();
        String id = IDUtil.id();
        hotel.setId(id);
        hotel.setTitle("hotel title" + i);
        hotel.setPicUrl("hotel-" + i + ".jpeg");
        hotel.setCommentNum(i);
        hotel.setLikeNum(i);
        hotel.setTimestamp(System.currentTimeMillis());
        hotelController.save(hotel);
    }
}
