package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.HotelDtl;
import com.panzhihua.travel.service.HotelDtlService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hotelDtl")
@AllArgsConstructor
public class HotelDtlController {
    private HotelDtlService hotelDtlService;

    @DeleteMapping("delete")
    public void delete(String id) {
        hotelDtlService.delete(id);
    }

    @PostMapping("save")
    public HotelDtl save(HotelDtl hotelDtl) {
        return hotelDtlService.save(hotelDtl);
    }

    @PutMapping("edit")
    public HotelDtl edit(HotelDtl hotelDtl) {
        return hotelDtlService.save(hotelDtl);
    }
}
