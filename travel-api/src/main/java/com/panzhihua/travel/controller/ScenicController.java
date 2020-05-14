package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Scenic;
import com.panzhihua.travel.entity.ScenicDtl;
import com.panzhihua.travel.service.ScenicDtlService;
import com.panzhihua.travel.service.ScenicService;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/scenic")
@AllArgsConstructor
public class ScenicController {
    private ScenicService scenicService;
    private ScenicDtlService scenicDtlService;

    @GetMapping("list")
    public RetDto list(QueryCondition queryCondition) {
        return scenicService.find(queryCondition);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        scenicService.delete(id);
    }

    @PostMapping("save")
    public RetDto save(@RequestBody ScenicDtl scenicDtl) {
        String id = IDUtil.id();
        scenicDtl.setId(id);

        Scenic scenic = new Scenic();
        scenic.setTitle(scenicDtl.getTitle());
        scenic.setPicUrl(scenicDtl.getPicUrl());
        scenic.setId(id);
        scenic.setTimestamp(System.currentTimeMillis());

        scenicDtlService.save(scenicDtl);

        return scenicService.save(scenic);
    }

    @PutMapping("edit")
    public RetDto edit(Scenic scenic) {
        return scenicService.save(scenic);
    }
}
