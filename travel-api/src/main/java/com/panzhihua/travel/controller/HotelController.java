package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Hotel;
import com.panzhihua.travel.service.HotelService;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hotel")
@AllArgsConstructor
public class HotelController {
    private HotelService hotelService;

    @GetMapping("findById")
    public RetDto findById(String id){
        return hotelService.findById(id);
    }

    @GetMapping("list")
    public RetDto list(QueryCondition queryCondition) {
        return hotelService.find(queryCondition);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        hotelService.delete(id);
    }

    @PostMapping("save")
    public RetDto save(@RequestBody Hotel hotel) {
        hotel.setId(IDUtil.id());
        hotel.setTimestamp(System.currentTimeMillis());
        return hotelService.save(hotel);
    }

    @PostMapping("edit")
    public RetDto edit(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }
}
