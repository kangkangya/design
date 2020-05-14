package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Banner;
import com.panzhihua.travel.service.BannerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/banner")
@AllArgsConstructor
public class BannerController {
    private BannerService bannerService;

    @DeleteMapping("delete")
    public void delete(String id) {
        bannerService.delete(id);
    }

    @PostMapping("save")
    public Banner save(Banner banner) {
        return bannerService.save(banner);
    }

    @PutMapping("edit")
    public Banner edit(Banner banner) {
        return bannerService.save(banner);
    }
}
