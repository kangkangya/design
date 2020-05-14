package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Scenic;
import com.panzhihua.travel.entity.ScenicDtl;
import com.panzhihua.travel.service.ScenicDtlService;
import com.panzhihua.travel.service.ScenicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/scenic-dtl")
@AllArgsConstructor
public class ScenicDtlController {
    private ScenicDtlService scenicDtlService;
    private ScenicService scenicService;

    @GetMapping("findById")
    public RetDto findById(String id) {
        return scenicDtlService.findById(id);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        scenicDtlService.delete(id);
    }

    @PostMapping("save")
    public RetDto save(ScenicDtl scenic) {
        return scenicDtlService.save(scenic);
    }

    @PostMapping("edit")
    public RetDto edit(@RequestBody ScenicDtl scenic) {
        Scenic scen = new Scenic();
        scen.setId(scenic.getId());
        scen.setTitle(scenic.getTitle());
        scen.setPicUrl(scenic.getPicUrl());
        scen.setTimestamp(System.currentTimeMillis());
        scenicService.save(scen);
        return scenicDtlService.save(scenic);
    }
}
