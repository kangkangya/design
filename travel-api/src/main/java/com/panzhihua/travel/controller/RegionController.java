package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.Region;
import com.panzhihua.travel.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/region")
@AllArgsConstructor
public class RegionController {
    private RegionService regionService;

    @GetMapping("list")
    public List<Region> list() {
        return regionService.list();
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        regionService.delete(id);
    }

    @PostMapping("save")
    public Region save(Region region) {
        return regionService.save(region);
    }

    @PutMapping("edit")
    public Region edit(Region region) {
        return regionService.save(region);
    }
}
