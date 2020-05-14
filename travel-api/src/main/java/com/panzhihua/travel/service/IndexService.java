package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.BannerRepository;
import com.panzhihua.travel.dao.HotelRepository;
import com.panzhihua.travel.dao.ScenicRepository;
import com.panzhihua.travel.entity.Banner;
import com.panzhihua.travel.entity.Hotel;
import com.panzhihua.travel.entity.Scenic;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IndexService {
    private BannerRepository bannerRepository;
    private HotelRepository hotelRepository;
    private ScenicRepository scenicRepository;

    public List<Banner> getBannerList() {
        return bannerRepository.findAll();
    }

    public List<Hotel> getHotelList() {
        List<Hotel> hotelList = hotelRepository
                .findAll(Sort.by(Sort.Order.desc("likeNum")));

        if (CollectionUtils.isEmpty(hotelList)) {
            hotelList = new ArrayList<>(0);
        } else if (hotelList.size() >= 6){
            hotelList = hotelList.subList(0, 6);
        }

        return hotelList;
    }

    public List<Scenic> getScenicList() {
        List<Scenic> scenicList = scenicRepository.findAll(Sort.by(Sort.Order.desc("likeNum")));

        if (CollectionUtils.isEmpty(scenicList)) {
            scenicList = new ArrayList<>(0);
        } else if(scenicList.size() >= 8){
            scenicList = scenicList.subList(0, 8);
        }

        return scenicList;
    }
}
