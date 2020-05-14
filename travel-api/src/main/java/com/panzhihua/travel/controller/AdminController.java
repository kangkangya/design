package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Admin;
import com.panzhihua.travel.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    private AdminService adminService;

    @GetMapping("login")
    public RetDto login(@RequestBody Admin admin){
        return adminService.login(admin.getUsername(),admin.getPassword());
    }
}
