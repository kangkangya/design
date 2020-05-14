package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.BannerRepository;
import com.panzhihua.travel.entity.Banner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;

    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    public void delete(String id) {
        bannerRepository.deleteById(id);
    }

    public List<Banner> list() {

        return bannerRepository.findAll();
    }
}
