package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.HotGoWith;
import com.panzhihua.travel.service.HotGoWithService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hot-go-with")
@AllArgsConstructor
public class HotGoWithController {
    private HotGoWithService hotGoWithService;

    @GetMapping("list")
    public List<HotGoWith> list() {
        return hotGoWithService.findAll();
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        hotGoWithService.delete(id);
    }

    @PostMapping("save")
    public HotGoWith save(HotGoWith hotGoWith) {
        return hotGoWithService.save(hotGoWith);
    }

    @PutMapping("edit")
    public HotGoWith edit(HotGoWith hotGoWith) {
        return hotGoWithService.save(hotGoWith);
    }
}
