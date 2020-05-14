package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.GoWithQueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.GoWith;
import com.panzhihua.travel.service.GoWithService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("api/go-with")
@AllArgsConstructor
public class GoWithController {
    private GoWithService goWithService;

    @GetMapping("hotGoWithList")
    public RetDto hotGoWithList(){
        return goWithService.hotGoWithList();
    }
    @GetMapping("list")
    public RetDto list(GoWithQueryCondition queryCondition) {
        return goWithService.list(queryCondition);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        goWithService.delete(id);
    }

    @PostMapping("save")
    public RetDto save(@RequestBody GoWith goWith) throws Exception {
        return goWithService.save(goWith);
    }

    @GetMapping("findById")
    public RetDto findById(String id){
        return goWithService.findById(id);
    }

    @PutMapping("edit")
    public RetDto edit(@RequestBody GoWith goWith) throws Exception {
        return goWithService.save(goWith);
    }

    @PostMapping("update")
    public RetDto update(@RequestBody GoWith goWith){
        return goWithService.update(goWith);
    }

    @GetMapping("findByUserId")
    public RetDto findByUserId(String refId){
        return goWithService.findByUserId(refId);
    }
}
