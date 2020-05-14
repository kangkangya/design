package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.TravelNote;
import com.panzhihua.travel.service.TravelNoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/travel-notes")
@AllArgsConstructor
public class TravelNoteController {
    private TravelNoteService travelNoteService;

    @GetMapping("list")
    public RetDto list(QueryCondition queryCondition) {
        return travelNoteService.find(queryCondition);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        travelNoteService.delete(id);
    }

    @PostMapping("save")
    public RetDto save(@RequestBody TravelNote travelNote) {
        return travelNoteService.save(travelNote);
    }

    @PutMapping("edit")
    public RetDto edit(TravelNote travelNote) {
        return travelNoteService.save(travelNote);
    }

    @GetMapping("findByUserId")
    public RetDto findByUserId(String refId){
        return travelNoteService.findByUserId(refId);
    }
}
