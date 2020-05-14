package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Banner;
import com.panzhihua.travel.entity.Hotel;
import com.panzhihua.travel.entity.Scenic;
import com.panzhihua.travel.service.IndexService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/index")
@AllArgsConstructor
public class IndexController {
    private IndexService indexService;

    @GetMapping("banner-list")
    public List<Banner> bannerList() {
        return indexService.getBannerList();
    }

    @GetMapping("scenic-list")
    public List<Scenic> scenicList() {
        return indexService.getScenicList();
    }

    @GetMapping("hotel-list")
    public List<Hotel> hotelList() {
        return indexService.getHotelList();
    }
}
