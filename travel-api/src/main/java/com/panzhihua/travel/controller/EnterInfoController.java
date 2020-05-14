package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.EnterInfo;
import com.panzhihua.travel.service.EnterInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
@RequestMapping("api/enterInfo")
public class EnterInfoController {

    @Resource
    private EnterInfoService enterInfoService;

    @PostMapping("save")
    public RetDto save(@RequestBody EnterInfo enterInfo){
        return enterInfoService.save(enterInfo);
    }

    @GetMapping("findByUserId")
    public RetDto findByUserId(String userId,String goWithId){
        return enterInfoService.findByUserId(userId,goWithId);
    }

    @GetMapping("delete")
    public void delete(String id){
        enterInfoService.delete(id);
    }
}
