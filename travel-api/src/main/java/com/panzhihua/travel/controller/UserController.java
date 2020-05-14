package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.User;
import com.panzhihua.travel.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("login")
    public RetDto<User> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("register")
    public RetDto<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        userService.delete(id);
    }

    @PostMapping("edit")
    public RetDto edit(@RequestBody User user) {
        return userService.edit(user);
    }

    @PostMapping("save")
    public RetDto save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("list")
    public RetDto list(QueryCondition condition) {
        return userService.list(condition);
    }
}
