package com.panzhihua.travel.controller;

import com.panzhihua.travel.entity.TestA;
import com.panzhihua.travel.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
@AllArgsConstructor
public class TestController {
    private TestService testService;

    @DeleteMapping("delete")
    public void delete(String id) {
        testService.delete1(id);
    }

    @PostMapping("save")
    public TestA save(@RequestBody TestA test) {
        return testService.save(test);
    }

    @PutMapping("edit")
    public TestA edit(@RequestBody TestA test) {
        return testService.save(test);
    }
}