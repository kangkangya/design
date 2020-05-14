package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Follow;
import com.panzhihua.travel.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
@RequestMapping("api/follow")
public class FollowController {
    @Resource
    private FollowService followService;

    @PostMapping("save")
    public RetDto save(@RequestBody Follow follow){
        return followService.save(follow);
    }

    @PostMapping("findOne")
    public RetDto findOne(@RequestBody Follow follow){
        return followService.findOne(follow);
    }

    @GetMapping("delete")
    public void delete(String id){
        followService.delete(id);
    }

}
