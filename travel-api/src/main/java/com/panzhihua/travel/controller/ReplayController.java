package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.ret.ReplyDto;
import com.panzhihua.travel.entity.Replay;
import com.panzhihua.travel.service.ReplayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/replay")
@AllArgsConstructor
public class ReplayController {
    private ReplayService replayService;

    @GetMapping("list")
    public List<ReplyDto> list(String id) {
        return replayService.findById(id);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        replayService.delete(id);
    }

    @PostMapping("save")
    public Replay save(@RequestBody Replay replay) {
        return replayService.save(replay);
    }

    @PutMapping("edit")
    public Replay edit(Replay replay) {
        return replayService.save(replay);
    }
}
